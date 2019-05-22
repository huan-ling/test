package com.blizzard.common.enums;

/**
 * @Description: 职业
 * @Author: Huan
 * @CreateTime: 2019-02-15 14:27
 */
public enum CareerEnum {
    DEROOY(0,"德鲁伊"),
    HUNTSMAN(1, "猎人"),
    MAGE(2, "法师"),
    PALADIN(3, "圣骑士"),
    PASTOR(4, "牧师"),
    STALKER(5, "潜行者"),
    SHAMAN(6,"萨满祭司"),
    WAELOCK(7, "术士"),
    WARRIOR(8, "战士"),
    NEUTRALITY(8, "中立")
    ;

    private int no;
    private String desc;

    CareerEnum(int no, String desc) {
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
