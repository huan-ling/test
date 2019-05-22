package com.blizzard.game.pojo;

import java.io.Serializable;

/**
 * @Description: 卡牌数量Dto
 * @Author: Huan
 * @CreateTime: 2019-03-01 20:30
 */
public class CardNumDto implements Serializable {
    private static final long serialVersionUID = 4968089874202813812L;
    private Integer upHandNum;
    private Integer upLibraryNum;
    private Integer downLibararyNum;

    public Integer getUpHandNum() {
        return upHandNum;
    }

    public void setUpHandNum(Integer upHandNum) {
        this.upHandNum = upHandNum;
    }

    public Integer getUpLibraryNum() {
        return upLibraryNum;
    }

    public void setUpLibraryNum(Integer upLibraryNum) {
        this.upLibraryNum = upLibraryNum;
    }

    public Integer getDownLibararyNum() {
        return downLibararyNum;
    }

    public void setDownLibararyNum(Integer downLibararyNum) {
        this.downLibararyNum = downLibararyNum;
    }

    @Override
    public String toString() {
        return "CardNumDto{" +
                "upHandNum=" + upHandNum +
                ", upLibraryNum=" + upLibraryNum +
                ", downLibararyNum=" + downLibararyNum +
                '}';
    }
}
