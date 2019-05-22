package com.blizzard.common.enums;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-21 16:19
 */
public enum  RarityEnum {
    LEGEND(0,"传说"),
    EPIC(1,"史诗"),
    RARE(2,"稀有"),
    NORMAL(3,"普通"),
    WITHOUT(4,"");

    RarityEnum(int no,String desc){
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
