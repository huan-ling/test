package com.blizzard.common.enums;

/**
 * @Description: 数据传输的类型
 * @Author: Huan
 * @CreateTime: 2019-02-23 16:25
 */
public enum  DataTypeEnum {

    MATCH(0,"匹配游戏"),
    NOT_MATCH(1,"不匹配游戏"),
    PLAY_CARD(2,"打出卡牌信息"),
    BEGIN_GAME(3,"开始游戏"),
    BEGIN_ROUND(4,"开始回合"),
    PLAY(5,"出牌"),
    CHOOSE_CARD(6,"选择卡牌"),
    MANA(6,"法力值"),
    CARD_UPDATE(7,"卡牌信息变更"),
    CARD_NUM_UPDATE(8,"卡牌数量变更"),
    HERO_INFO(9,"英雄信息变更"),
    END_GAME(10,"结束游戏")
    ;

    DataTypeEnum(int no,String desc){
        this.no = no;
        this.desc = desc;
    }
    private int no;
    private String desc;

    public int getNo() {
        return no;
    }

    public String getDesc() {
        return desc;
    }
}
