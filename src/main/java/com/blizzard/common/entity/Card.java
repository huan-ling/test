package com.blizzard.common.entity;

import com.blizzard.common.enums.*;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @Description: 卡牌的基本属性
 * @Author: Huan
 * @CreateTime: 2019-02-15 14:11
 */
public class Card implements Serializable {

    private static final long serialVersionUID = -2974560364117111550L;
    private int id;
    /**
     * 卡牌的类型
     */
    private CardTypeEnum cardTypeEnum;
    /**
     * 卡牌的职业
     */
    private CareerEnum careerEnum;
    /**
     * 卡牌的名字
     */
    private String name;
    /**
     * 卡牌的描述
     */
    private String desc;
    /**
     * 卡牌的关键字数组
     */
    private KeyWordEnum[] keyWordEnums;
    /**
     * 卡牌的法力值
     */
    private int manaValue;

    private String img;

    /**
     * 卡牌稀有度
     */
    private RarityEnum rarityEnum;


    /**
     * ====================以下是英雄牌特有的属性========================
     */
    /**
     * 护甲值
     */
    private int armor;
    /**
     * ====================以下是随从牌特有的属性========================
     */

    /**
     * 卡牌的种族
     */
    private CardRaceEnum cardRaceEnum;
    /**
     * 卡牌的攻击力
     */
    private int attackForce;
    /**
     * 卡牌的血量
     */
    private int bloodVolume;
    /**
     * ====================以下是武器牌特有的属性========================
     */
    /**
     * 卡牌的耐久度
     */
    private int durability;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardTypeEnum getCardTypeEnum() {
        return cardTypeEnum;
    }

    public void setCardTypeEnum(CardTypeEnum cardTypeEnum) {
        this.cardTypeEnum = cardTypeEnum;
    }

    public CareerEnum getCareerEnum() {
        return careerEnum;
    }

    public void setCareerEnum(CareerEnum careerEnum) {
        this.careerEnum = careerEnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public KeyWordEnum[] getKeyWordEnums() {
        return keyWordEnums;
    }

    public void setKeyWordEnums(KeyWordEnum[] keyWordEnums) {
        this.keyWordEnums = keyWordEnums;
    }

    public int getManaValue() {
        return manaValue;
    }

    public void setManaValue(int manaValue) {
        this.manaValue = manaValue;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public CardRaceEnum getCardRaceEnum() {
        return cardRaceEnum;
    }

    public void setCardRaceEnum(CardRaceEnum cardRaceEnum) {
        this.cardRaceEnum = cardRaceEnum;
    }

    public int getAttackForce() {
        return attackForce;
    }

    public void setAttackForce(int attackForce) {
        this.attackForce = attackForce;
    }

    public int getBloodVolume() {
        return bloodVolume;
    }

    public void setBloodVolume(int bloodVolume) {
        this.bloodVolume = bloodVolume;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public RarityEnum getRarityEnum() {
        return rarityEnum;
    }

    public void setRarityEnum(RarityEnum rarityEnum) {
        this.rarityEnum = rarityEnum;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardTypeEnum=" + cardTypeEnum +
                ", careerEnum=" + careerEnum +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", keyWordEnums=" + Arrays.toString(keyWordEnums) +
                ", manaValue=" + manaValue +
                ", img='" + img + '\'' +
                ", rarityEnum=" + rarityEnum +
                ", armor=" + armor +
                ", cardRaceEnum=" + cardRaceEnum +
                ", attackForce=" + attackForce +
                ", bloodVolume=" + bloodVolume +
                ", durability=" + durability +
                '}';
    }
}
