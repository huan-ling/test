package com.blizzard.game.pojo;

import java.io.Serializable;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-04-09 18:59
 */
public class HeroInfoDto implements Serializable {

    private static final long serialVersionUID = -6953383078776122943L;

    /**
     * 护甲
     */
    private int armor;
    /**
     * 血量
     */
    private int blood;

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    @Override
    public String toString() {
        return "HeroInfoDto{" +
                "armor=" + armor +
                ", blood=" + blood +
                '}';
    }
}
