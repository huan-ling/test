package com.blizzard.game.pojo;

import com.blizzard.common.enums.CareerEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-23 19:29
 */
public class Hero implements Serializable,Cloneable {

    private static final long serialVersionUID = -1871110346204257209L;
    /**
     * 用户id
     */
    private int uid;
    /**
     * 英雄的名称
     */
    private CareerEnum careerEnum;
    /**
     * 是否是先手===>改为是否是轮到本回合己方出牌
     */
    private boolean isFirst;
    /**
     * 血量
     */
    private int bloodVolume;
    /**
     * 护甲
     */
    private int armor;
    /**
     * 法力值
     */
    private int manaValue;
    /**
     * 下回合的法力增减 过载等情况，仅生效一回合
     */
    private int nextMana;
    /**
     * 当前法力值;
     */
    private int curMana;
    /**
     * 疲劳值
     */
    private int tiredValue;
    /**
     * 牌库
     */
    private List<CardGameDto> libraryCardList;
    /**
     *手牌
     */
    private List<CardGameDto> handCardList;
    /**
     * 场面
     */
    private List<CardGameDto> sceneCardList;

    public int getManaValue() {
        return manaValue;
    }

    public void setManaValue(int manaValue) {
        this.manaValue = manaValue;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public CareerEnum getCareerEnum() {
        return careerEnum;
    }

    public void setCareerEnum(CareerEnum careerEnum) {
        this.careerEnum = careerEnum;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public int getBloodVolume() {
        return bloodVolume;
    }

    public void setBloodVolume(int bloodVolume) {
        this.bloodVolume = bloodVolume;
    }

    public List<CardGameDto> getLibraryCardList() {
        return libraryCardList;
    }

    public void setLibraryCardList(List<CardGameDto> libraryCardList) {
        this.libraryCardList = libraryCardList;
    }

    public List<CardGameDto> getHandCardList() {
        return handCardList;
    }

    public void setHandCardList(List<CardGameDto> handCardList) {
        this.handCardList = handCardList;
    }

    public int getTiredValue() {
        return tiredValue;
    }

    public void setTiredValue(int tiredValue) {
        this.tiredValue = tiredValue;
    }

    public int getNextMana() {
        return nextMana;
    }

    public void setNextMana(int nextMana) {
        this.nextMana = nextMana;
    }

    public int getCurMana() {
        return curMana;
    }

    public void setCurMana(int curMana) {
        this.curMana = curMana;
    }

    public List<CardGameDto> getSceneCardList() {
        return sceneCardList;
    }

    public void setSceneCardList(List<CardGameDto> sceneCardList) {
        this.sceneCardList = sceneCardList;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return uid == hero.uid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }

    @Override
    public String toString() {
        return "Hero{" +
                "uid=" + uid +
                ", careerEnum=" + careerEnum +
                ", isFirst=" + isFirst +
                ", bloodVolume=" + bloodVolume +
                ", armor=" + armor +
                ", manaValue=" + manaValue +
                ", nextMana=" + nextMana +
                ", curMana=" + curMana +
                ", tiredValue=" + tiredValue +
                ", libraryCardList=" + libraryCardList +
                ", handCardList=" + handCardList +
                ", sceneCardList=" + sceneCardList +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Hero hero = (Hero) super.clone();
        if(hero.libraryCardList != null){
            hero.libraryCardList = (List<CardGameDto>) ((ArrayList)hero.libraryCardList).clone();
        }
        if(hero.handCardList != null){
            hero.handCardList = (List<CardGameDto>) ((ArrayList)hero.handCardList).clone();
        }
        return hero;
    }
}
