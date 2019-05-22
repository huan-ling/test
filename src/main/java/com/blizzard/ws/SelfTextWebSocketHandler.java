package com.blizzard.ws;

import com.blizzard.common.constant.RedisConstant;
import com.blizzard.common.enums.DataTypeEnum;
import com.blizzard.common.jedis.RedisService;
import com.blizzard.common.util.Assert;
import com.blizzard.common.util.IntegerUtil;
import com.blizzard.common.util.JsonUtil;
import com.blizzard.game.action.GameAction;
import com.blizzard.game.action.PrepareGameAction;
import com.blizzard.game.pojo.Game;
import com.blizzard.game.thread.GameThreadLocal;
import com.blizzard.ws.entity.Message;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-19 16:06
 */
@Component
public class SelfTextWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private RedisService redisService;
    @Autowired
    private PrepareGameAction prepareGameAction;
    @Autowired
    private GameAction gameAction;

    private static final Logger LOGGER = LoggerFactory.getLogger(SelfTextWebSocketHandler.class);
    // 应该放到缓存中去
    private static Map<Integer, WebSocketSession> userSocketSessionMap = new ConcurrentHashMap<>();

    // 维护游戏
    private static Map<String,Game> gameMap = new HashMap<>();


    public static Map<String, Game> getGameMap() {
        return gameMap;
    }

    /**
     * 建立连接后
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        Integer uid = (Integer)session.getAttributes().get("uid");
        LOGGER.info("Session {} connected.", uid);

        userSocketSessionMap.put(uid,session);
        LOGGER.info("当前在线用户数: {}", userSocketSessionMap.size());
    }

    /**
     * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message){
        if (message.getPayloadLength() == 0) {
            return;
        }
        Message msg = new Gson().fromJson(message.getPayload().toString(), Message.class);
        LOGGER.info("消息是{}",msg);
        DataTypeEnum dataTypeEnum = msg.getDataTypeEnum();
        Assert.checkNull(dataTypeEnum);
        Integer uid = (Integer) session.getAttributes().get("uid");
        switch (dataTypeEnum){
            case MATCH:
                // 需要匹配游戏
                String value = redisService.rPop(RedisConstant.MARCH_LIST);
                String deckIdStr = msg.getData().toString();
                if(StringUtils.isEmpty(value)){
                    // 表明当前没有待匹配的用户 则加入到缓存中等待匹配
                    value = String.valueOf(session.getAttributes().get("uid"))+"-"+deckIdStr;
                    redisService.lpush(RedisConstant.MARCH_LIST,value);
                }else{
                    // 匹配到用户 发送通知告知前端页面已匹配
                    String[] splitValues = value.split("-");
                    // 初始化游戏数据
                    Game game = prepareGameAction.parepareGame(uid,IntegerUtil.getInt(deckIdStr),
                            IntegerUtil.getInt(splitValues[0]),IntegerUtil.getInt(splitValues[1]));
                    gameMap.put(game.getGameId(),game);

                    Message resultMsg = new Message();
                    resultMsg.setDataTypeEnum(DataTypeEnum.MATCH);
                    resultMsg.setData(game.getGameId());
                    TextMessage textMessage = new TextMessage(JsonUtil.obj2Json(resultMsg));
                    sendMessageToUser(uid,textMessage);
                    sendMessageToUser(IntegerUtil.getInt(splitValues[0]),textMessage);
                }
            case NOT_MATCH:
                // 取消匹配

                break;
            case BEGIN_GAME:
                // 开始游戏
                String gameId = msg.getData().toString();
                String data = gameAction.beginGame(gameId,uid,gameMap);
                Message message2 = new Message();
                message2.setDataTypeEnum(DataTypeEnum.BEGIN_GAME);
                message2.setData(data);
                sendMessageToUser(uid,new TextMessage(JsonUtil.obj2Json(message2)));
                break;
                default:
        }
    }

    /**
     * 消息传输错误处理
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception){
        Integer uid = (Integer)session.getAttributes().get("uid");
        userSocketSessionMap.remove(uid);
        if (session.isOpen()) {
            try {
                session.close();
            } catch (Exception e) {
                LOGGER.warn("消息关闭异常"+e);
            }finally {
                session = null;
            }
        }
    }

    /**
     * 关闭连接后
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        LOGGER.info("Session {} disconnected. Because of {}", session.getId(), closeStatus);
        Integer uid = (Integer)session.getAttributes().get("uid");
        userSocketSessionMap.remove(uid);
        LOGGER.info("当前在线用户数: {}", userSocketSessionMap.values().size());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     * @throws IOException
     */
    public void broadcast(TextMessage message){
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("socket-schedule-pool-%d").daemon(true).build());
        Collection<WebSocketSession> values = userSocketSessionMap.values();
        Iterator<WebSocketSession> iterator = values.iterator();
        executorService.execute(()->{
            while(iterator.hasNext()){
                WebSocketSession next = iterator.next();
                try {
                    next.sendMessage(message);
                } catch (IOException e) {
                    LOGGER.error("发送消息时异常"+e.getMessage());
                }
                iterator.remove();
            }
        });
    }

    /**
     *  没有用户id的时候 通过绑定在线程中的游戏信息向双方用户发送信息
     * @param message
     */
    public void sendMessageToUser(Message message){
        message.setTo(Message.TO_SELF);
        sendMessageToUser(GameThreadLocal.getHeroUid(),message);
        message.setTo(Message.TO_ENEMY);
        sendMessageToUser(GameThreadLocal.getEnemyUid(),message);
    }

    public void sendMessageToUser(Integer uid, Message message){
        sendMessageToUser(uid,new TextMessage(JsonUtil.obj2Json(message)));
    }

    public void sendMessageToUser(Integer uid, DataTypeEnum dataTypeEnum, Object data){
        Message message = new Message();
        message.setDataTypeEnum(dataTypeEnum);
        message.setData(data);
        sendMessageToUser(uid,message);
    }

    /**
     * 给某个用户发送消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessageToUser(Integer uid, TextMessage message) {
        if(uid == null || message == null){
            return;
        }
        if(userSocketSessionMap.containsKey(uid)){
            try {
                userSocketSessionMap.get(uid).sendMessage(message);
            } catch (Exception e) {
                LOGGER.error("发送消息时产生错误"+e.getMessage());
            }
        }
    }
}
