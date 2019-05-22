package com.blizzard.game.action.impl;

import com.blizzard.common.constant.Constant;
import com.blizzard.common.entity.Card;
import com.blizzard.common.exception.BaseException;
import com.blizzard.common.util.BeanUtis;
import com.blizzard.common.util.JsonUtil;
import com.blizzard.game.action.GameAction;
import com.blizzard.game.pojo.CardGameDto;
import com.blizzard.game.pojo.Game;
import com.blizzard.game.pojo.Hero;
import com.blizzard.game.util.GameUtil;
import com.blizzard.manage.mapper.CardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-23 20:34
 */
@Component
public class GameActionImpl implements GameAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameActionImpl.class);

    @Autowired
    private CardMapper cardMapper;

    @Override
    public String beginGame(String gameId, Integer uid, Map<String, Game> gameMap) {
        Game game = gameMap.get(gameId);
        if(game == null){
            throw new BaseException(100,"游戏不存在gameId="+gameId);
        }
        Map<Integer, Hero> heroMap = game.getHeroMap();
        // 后手英雄
        Hero laterHero = null;
        // 0表示第一回合未开始需要选牌
        if(game.getBoundNum() == 0){
            for(Hero hero : game.getHeroMap().values()){
                if(!hero.isFirst() && uid.equals(hero.getUid())){
                    laterHero = hero;
                }
            }
        }
        // 返回客户端精简部分数据
        try {
            Game cloneGame = game.clone();
            if(game.getBoundNum() == 0){
                if(laterHero != null){// 后手英雄自动获得一个硬币
                    addLucky(laterHero);
                }
            }
            heroMap = cloneGame.getHeroMap();
            for(Map.Entry<Integer,Hero> entry : heroMap.entrySet()){
                if(!entry.getKey().equals(uid)){
                    entry.getValue().setHandCardList(null);
                }
                entry.getValue().setLibraryCardList(null);
            }
            return JsonUtil.obj2Json(cloneGame);
        } catch (CloneNotSupportedException e) {
            LOGGER.error("复制对象错误",e);
            throw new BaseException(100,"复制对象错误");
        }
    }

    /**
     * 增加幸运币
     * @param hero
     */
    private void addLucky(Hero hero){
        Card card = cardMapper.getById(Constant.LUCKY_CARD_ID);
        CardGameDto cardGameDto = new CardGameDto();
        BeanUtis.copyProperties(card,cardGameDto);
        hero.getHandCardList().add(cardGameDto);
    }
}
