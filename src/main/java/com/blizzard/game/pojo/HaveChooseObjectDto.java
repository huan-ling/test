package com.blizzard.game.pojo;

import com.blizzard.common.enums.ChooseObjectEnum;

import java.io.Serializable;

/**
 * @Description: 已选择卡牌
 * @Author: Huan
 * @CreateTime: 2019-02-27 20:19
 */
public class HaveChooseObjectDto implements Serializable {

    private static final long serialVersionUID = -7194833890103867671L;

    private Integer cardId;
    /**
     * 作用的卡牌的位置 -2表示对方英雄 -1表示友方英雄
     */
    private Integer toSite;
    /**
     * 打出的卡牌在手牌中的位置
     */
    private Integer handSite;
    /**
     * 打出的卡牌在场面中的位置
     */
    private Integer sceneSite;
    /**
     * 打出对用对象的类型
     */
    private ChooseObjectEnum chooseObjectEnum;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getToSite() {
        return toSite;
    }

    public void setToSite(Integer toSite) {
        this.toSite = toSite;
    }

    public Integer getHandSite() {
        return handSite;
    }

    public void setHandSite(Integer handSite) {
        this.handSite = handSite;
    }

    public Integer getSceneSite() {
        return sceneSite;
    }

    public void setSceneSite(Integer sceneSite) {
        this.sceneSite = sceneSite;
    }

    public ChooseObjectEnum getChooseObjectEnum() {
        return chooseObjectEnum;
    }

    public void setChooseObjectEnum(ChooseObjectEnum chooseObjectEnum) {
        this.chooseObjectEnum = chooseObjectEnum;
    }

    @Override
    public String toString() {
        return "HaveChooseObjectDto{" +
                "cardId=" + cardId +
                ", toSite=" + toSite +
                ", handSite=" + handSite +
                ", sceneSite=" + sceneSite +
                ", chooseObjectEnum=" + chooseObjectEnum +
                '}';
    }
}
