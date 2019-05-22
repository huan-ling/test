package com.blizzard.game.pojo;

import java.io.Serializable;

/**
 * @Description: 法力值Dto
 * @Author: Huan
 * @CreateTime: 2019-03-01 16:32
 */
public class ManaDto implements Serializable {

    private static final long serialVersionUID = 8762538064776861968L;
    private Integer mana;
    private Integer anotherMana;

    public Integer getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public Integer getAnotherMana() {
        return anotherMana;
    }

    public void setAnotherMana(int anotherMana) {
        this.anotherMana = anotherMana;
    }

    @Override
    public String toString() {
        return "ManaDto{" +
                "mana=" + mana +
                ", anotherMana=" + anotherMana +
                '}';
    }
}
