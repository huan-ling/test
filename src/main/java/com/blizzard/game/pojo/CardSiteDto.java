package com.blizzard.game.pojo;

import java.io.Serializable;

/**
 * @Description: 卡牌位置Dto
 * @Author: Huan
 * @CreateTime: 2019-03-02 17:49
 */
public class CardSiteDto implements Serializable {

    private static final long serialVersionUID = -2144944966849418775L;
    private Integer site;
    private CardGameDto cardGameDto;

    public Integer getSite() {
        return site;
    }

    public void setSite(Integer site) {
        this.site = site;
    }

    public CardGameDto getCardGameDto() {
        return cardGameDto;
    }

    public void setCardGameDto(CardGameDto cardGameDto) {
        this.cardGameDto = cardGameDto;
    }

    @Override
    public String toString() {
        return "CardSiteDtoUtil{" +
                "site=" + site +
                ", cardGameDto=" + cardGameDto +
                '}';
    }
}
