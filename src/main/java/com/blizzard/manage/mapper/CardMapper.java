package com.blizzard.manage.mapper;

import com.blizzard.common.entity.Card;
import com.blizzard.common.enums.CareerEnum;

import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-16 13:56
 */
public interface CardMapper {
    void add(Card card);

    void batchAdd(List<Card> cardList);

    List<Card> getAll();

    List<Card> getCareerAll(CareerEnum careerEnum);

    /**
     * 从传入卡牌的id获得为传说卡牌的id集合
     *
     * @param cardIdList
     * @return
     */
    List<Integer> filterLegendCard(List<Integer> cardIdList);

    Card getById(int id);
}
