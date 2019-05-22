package com.blizzard.manage.service.impl;

import com.blizzard.common.constant.RedisConstant;
import com.blizzard.common.entity.Card;
import com.blizzard.common.enums.CareerEnum;
import com.blizzard.common.jedis.RedisService;
import com.blizzard.common.util.JsonUtil;
import com.blizzard.common.util.StringUtil;
import com.blizzard.manage.action.CardAction;
import com.blizzard.manage.mapper.CardMapper;
import com.blizzard.manage.service.CardSerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-16 13:53
 */
@Service("cardService")
public class CardServiceImpl implements CardSerivce {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardServiceImpl.class);

    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private CardAction cardAction;
    @Autowired
    private RedisService redisService;

    @Override
    public void add(Card card) {
       cardMapper.add(card);
    }

    @Override
    public void batchAdd(String url) {
        //"http://www.hstopdeck.com/cards?card_name&card_cost&card_format&card_rarity&card_type&card_class&card_set=boomsday&card_mechanic&card_race&view=table"
        List<Card> cardList = cardAction.getCardByReptile(url);
        LOGGER.info("获得卡牌的信息为"+cardList);
        cardMapper.batchAdd(cardList);
    }

    @Override
    public List<Card> getAll() {
        String json = redisService.get(RedisConstant.CARD_ALL);
        List<Card> cardList;
        if(StringUtil.isNull(json)){
            cardList = cardMapper.getAll();
            redisService.set(RedisConstant.CARD_ALL,JsonUtil.obj2Json(cardList),60);
        }else{
            cardList = JsonUtil.json2List(json, Card.class);
        }
        return cardList;
    }

    @Override
    public List<Card> getCareerAll(CareerEnum careerEnum) {
        return cardMapper.getCareerAll(careerEnum);
    }

}
