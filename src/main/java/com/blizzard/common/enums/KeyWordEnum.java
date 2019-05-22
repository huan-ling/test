package com.blizzard.common.enums;

/**
 * @Description: 关键字枚举
 * @Author: Huan
 * @CreateTime: 2019-02-15 12:50
 */
public enum KeyWordEnum {

    BATTLECRY(0, "战吼"),
    DEATHRATTLE(1, "亡语"),
    TAUNT(2, "嘲讽"),
    DIVINE_SHIELD(3, "圣盾"),
    CHARGE(4, "冲锋"),
    POUNCE(5, "突袭"),
    DISCOVER(6, "发现"),
    PASSIVITY(7, "被动"),
    TOXIC(8, "剧毒"),
    LEECH(9, "吸血"),
    SNEAK(10, "潜行"),
    OVERLOAD(11, "过载")
    ;

    private int no;
    private String desc;

    KeyWordEnum(int no,String desc){
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
