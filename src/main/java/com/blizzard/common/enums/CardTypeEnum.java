package com.blizzard.common.enums;

/**
 * @Description: 卡牌类型的枚举
 * @Author: Huan
 * @CreateTime: 2019-02-15 12:43
 */
public enum CardTypeEnum {

    RETINUE(0, "随从牌"),
    MAGIC(1, "法术牌"),
    HERO(2, "英雄牌"),
    WEAPON(3, "武器牌"),
    OTHER(4, "其它牌");

    private int no;
    private String desc;


    CardTypeEnum(int no, String desc) {
        this.no = no;
        this.desc = desc;
    }

    public int getNo() {
        return no;
    }

    public String getDesc() {
        return desc;
    }
}
