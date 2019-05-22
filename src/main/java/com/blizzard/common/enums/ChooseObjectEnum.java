package com.blizzard.common.enums;

/**
 * @Description: 选择对象的枚举
 * @Author: Huan
 * @CreateTime: 2019-02-27 19:54
 */
public enum ChooseObjectEnum {
    ENEMY_RETINUE(1,"敌方随从"),
    ENEMY(2,"敌方单位"),
    FRIEND_RETINUE(3,"友方随从"),
    FRIEND(4,"友方单位"),
    RETINUE(5,"随从"),
    HERO(6,"英雄"),
    OTHER(7,"其它");
    private int no;
    private String desc;

    ChooseObjectEnum(int no,String desc){
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
