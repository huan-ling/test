package com.blizzard.game.pojo;

import java.io.Serializable;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-24 22:03
 */
public class RoundDto implements Serializable {

    private static final long serialVersionUID = 6289112544662353684L;
    /**
     * 抽牌数量
     */
    private int drawCardNum;
    private int tiredValue;
    private int manaValue;

    public int getManaValue() {
        return manaValue;
    }

    public void setManaValue(int manaValue) {
        this.manaValue = manaValue;
    }

    public int getDrawCardNum() {
        return drawCardNum;
    }

    public void setDrawCardNum(int drawCardNum) {
        this.drawCardNum = drawCardNum;
    }

    public int getTiredValue() {
        return tiredValue;
    }

    public void setTiredValue(int tiredValue) {
        this.tiredValue = tiredValue;
    }

    @Override
    public String toString() {
        return "RoundDto{" +
                "drawCardNum=" + drawCardNum +
                ", tiredValue=" + tiredValue +
                ", manaValue=" + manaValue +
                '}';
    }
}
