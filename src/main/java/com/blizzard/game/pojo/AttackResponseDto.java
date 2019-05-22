package com.blizzard.game.pojo;

import java.io.Serializable;

/**
 * @Description: 随从攻击响应结果
 * @Author: Huan
 * @CreateTime: 2019-03-06 15:08
 */
public class AttackResponseDto implements Serializable {

    private static final long serialVersionUID = 3135209173515404483L;
    private Integer heroVolume;

    private CardSiteDto cardSiteDto;

    private Integer enmeyHeroVolume;

    private CardSiteDto enemyCardSiteDto;

    public Integer getHeroVolume() {
        return heroVolume;
    }

    public void setHeroVolume(Integer heroVolume) {
        this.heroVolume = heroVolume;
    }

    public CardSiteDto getCardSiteDto() {
        return cardSiteDto;
    }

    public void setCardSiteDto(CardSiteDto cardSiteDto) {
        this.cardSiteDto = cardSiteDto;
    }

    public Integer getEnmeyHeroVolume() {
        return enmeyHeroVolume;
    }

    public void setEnmeyHeroVolume(Integer enmeyHeroVolume) {
        this.enmeyHeroVolume = enmeyHeroVolume;
    }

    public CardSiteDto getEnemyCardSiteDto() {
        return enemyCardSiteDto;
    }

    public void setEnemyCardSiteDto(CardSiteDto enemyCardSiteDto) {
        this.enemyCardSiteDto = enemyCardSiteDto;
    }

    @Override
    public String toString() {
        return "AttackResponseDto{" +
                "heroVolume=" + heroVolume +
                ", cardSiteDto=" + cardSiteDto +
                ", enmeyHeroVolume=" + enmeyHeroVolume +
                ", enemyCardSiteDto=" + enemyCardSiteDto +
                '}';
    }
}
