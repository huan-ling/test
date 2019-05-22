package com.blizzard.game.util;

import com.blizzard.common.constant.Constant;
import com.blizzard.common.enums.CardRaceEnum;
import com.blizzard.common.exception.BaseException;
import com.blizzard.common.spring.SpringUtil;
import com.blizzard.game.card.CardInt;
import com.blizzard.game.pojo.CardGameDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-03-01 13:23
 */
public class CardUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardUtil.class);

    public static CardInt getCardInt(Integer cardId) {
        CardInt cardInt = getCardInt(Constant.CARD_PACAKAGE + cardId);
        if(cardInt == null){
            try {
                Class<?> clz = Class.forName(Constant.CARD_BASE_PACAKAGE);
                cardInt = (CardInt) SpringUtil.getBean("cardInt",clz);
            } catch (ClassNotFoundException e) {
                LOGGER.error("类型找不到异常",e);
                throw new BaseException("类型找不到异常");
            }
        }
        return cardInt;
    }

    private static CardInt getCardInt(String className){
        try {
            Class<?> clz = Class.forName(className);
            return (CardInt) SpringUtil.getBean(clz);
        } catch (ClassNotFoundException e) {
            LOGGER.warn("卡牌cardId={}没有特殊的处理策略",className);
            return null;
        }
    }

    /**
     * 获得卡牌种族对应的所有卡牌信息
     *
     * @param cardGameDtoList
     * @param cardRaceEnum
     * @return
     */
    public static List<Integer> getIndexListByCardRace(List<CardGameDto> cardGameDtoList, CardRaceEnum cardRaceEnum){
        List<Integer> list = new ArrayList<>();
        int index = 0;
        for(CardGameDto cardGameDto : cardGameDtoList){
            if(cardRaceEnum.equals(cardGameDto.getCardRaceEnum())){
                list.add(index);
            }
            index++;
        }
        return list;
    }

}
