package com.blizzard.common.enums;

/**
 * @Description: 卡牌种族
 * @Author: Huan
 * @CreateTime: 2019-02-15 13:47
 */
public enum CardRaceEnum {
    /**
     * 无种族
     */
    WITHOUT(0, ""),
    WILD(1, "野兽"),
    ELEMENT(2, "元素"),
    EVIL(3, "恶魔"),
    MACHINE(4, "机械"),
    MERMAN(5, "鱼人"),
    PIRATE(6, "海盗");


    private int no;
    private String desc;

    CardRaceEnum(int no, String desc) {
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
