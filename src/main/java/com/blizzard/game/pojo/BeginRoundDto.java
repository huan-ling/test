package com.blizzard.game.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-24 21:45
 */
public class BeginRoundDto implements Serializable {

    private static final long serialVersionUID = 1768866659329773145L;
    /**
     * 疲劳值
     */
    private int tiredValue;
    /**
     * 抽取的卡牌
     */
    private List<CardGameDto> cardGameDtoList;
    /**
     * 法力值
     */
    private int manaValue;

    public int getTiredValue() {
        return tiredValue;
    }

    public void setTiredValue(int tiredValue) {
        this.tiredValue = tiredValue;
    }

    public List<CardGameDto> getCardGameDtoList() {
        return cardGameDtoList;
    }

    public void setCardGameDtoList(List<CardGameDto> cardGameDtoList) {
        this.cardGameDtoList = cardGameDtoList;
    }

    public int getManaValue() {
        return manaValue;
    }

    public void setManaValue(int manaValue) {
        this.manaValue = manaValue;
    }

    @Override
    public String toString() {
        return "BeginRoundDto{" +
                "tiredValue=" + tiredValue +
                ", cardGameDtoList=" + cardGameDtoList +
                ", manaValue=" + manaValue +
                '}';
    }
}
