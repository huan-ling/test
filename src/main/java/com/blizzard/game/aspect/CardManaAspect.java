package com.blizzard.game.aspect;

import com.blizzard.common.constant.Constant;
import com.blizzard.common.enums.CardTypeEnum;
import com.blizzard.common.enums.DataTypeEnum;
import com.blizzard.common.exception.BaseException;
import com.blizzard.game.pojo.*;
import com.blizzard.game.util.CardSiteDtoUtil;
import com.blizzard.game.util.GameUtil;
import com.blizzard.game.util.MessageUtil;
import com.blizzard.manage.mapper.CardMapper;
import com.blizzard.ws.SelfTextWebSocketHandler;
import com.blizzard.ws.entity.Message;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-27 17:55
 */
@Component
@Aspect
public class CardManaAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardManaAspect.class);

    @Autowired
    private SelfTextWebSocketHandler webSocketHandler;

    /**
     * 1.通用方法扣除法力值
     * 2.移除手牌集合中的卡牌
     * 3.增加场面集合中的卡牌
     * 4.通过ws通知另一个玩家相关信息
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.blizzard.game.card..*.play*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 通用判断
        Object[] args = joinPoint.getArgs();
        String gameId = (String) args[0];
        Integer uid = (Integer)args[1];
        HaveChooseObjectDto chooseDto = (HaveChooseObjectDto)args[2];
        Hero hero = GameUtil.getHero(gameId, uid);
        List<CardGameDto> handCardList = hero.getHandCardList();
        CardGameDto cardGameDto = GameUtil.getCardByIndex(handCardList,chooseDto.getHandSite());
        if(hero.getCurMana() < cardGameDto.getManaValue()){
            throw new BaseException(100,"法力值不足");
        }
        CardTypeEnum cardTypeEnum = cardGameDto.getCardTypeEnum();
        if(cardTypeEnum.equals(CardTypeEnum.RETINUE) && hero.getSceneCardList().size() >= Constant.SCENE_CARD_MAX){
            throw new BaseException(100,"随从的场面空间不足");
        }
        // 执行目标方法
        Object proceed = joinPoint.proceed();
        if(joinPoint.getSignature().getName().equals("playPost")){
            Integer anotherUid = GameUtil.getAnotherUid(gameId, uid);
            // 1
            hero.setCurMana(hero.getCurMana()-cardGameDto.getManaValue());
            // 2
            GameUtil.removeByIndex(handCardList,chooseDto.getHandSite());
            // 3
            if(CardTypeEnum.RETINUE.equals(cardGameDto.getCardTypeEnum())){
                // TODO
                hero.getSceneCardList().add(chooseDto.getSceneSite(),cardGameDto);
                // 通过ws通知双方玩家的位置信息
                CardSiteDto cardSiteDto = CardSiteDtoUtil.getCardSiteDto(chooseDto.getSceneSite(),cardGameDto);
                Message message = MessageUtil.getMessage(DataTypeEnum.PLAY_CARD,cardSiteDto);
                webSocketHandler.sendMessageToUser(anotherUid,message);
            }
            // 4
            if(proceed != null){
                Message message = (Message)proceed;
                message.setTo(Message.TO_ENEMY);
                webSocketHandler.sendMessageToUser(anotherUid,message);
            }
            // 出牌成功 完成手牌数量更新的处理 移除一张牌
            webSocketHandler.sendMessageToUser(GameUtil.getAnotherUid(gameId,uid),MessageUtil.getMessage(DataTypeEnum.CARD_NUM_UPDATE,-1));
        }
        return proceed;
    }
}
