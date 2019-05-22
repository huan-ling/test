package com.blizzard.game.pojo;

import com.blizzard.common.entity.Card;
import com.blizzard.common.enums.CardRaceEnum;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-23 19:25
 */
public class CardGameDto extends Card implements Serializable {

    private static final long serialVersionUID = 1307058574649801216L;
    public static final int NOT_ATTACK = 0;
    public static final int CAN_ATTACK = 1;
    public static final int SURPRISE_ATTACk = 2;
    /**
     * buff
     */
    private List<Card> appendixList;

    /**
     * 相同的卡牌数量
     */
    private int num;

    /**
     * 可不可以攻击,0不可以攻击，1可以攻击，2，突袭
     */
    private int canAttack;
    @Override
    public CardRaceEnum getCardRaceEnum() {
        return super.getCardRaceEnum();
    }

    public List<Card> getAppendixList() {
        return appendixList;
    }

    public void setAppendixList(List<Card> appendixList) {
        this.appendixList = appendixList;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCanAttack() {
        return canAttack;
    }

    public void setCanAttack(int canAttack) {
        this.canAttack = canAttack;
    }

    @Override
    public String toString() {
        return "CardGameDto{" +
                "appendixList=" + appendixList +
                ", num=" + num +
                ", canAttack=" + canAttack +
                '}';
    }
}
