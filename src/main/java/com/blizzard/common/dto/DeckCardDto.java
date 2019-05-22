package com.blizzard.common.dto;

import com.blizzard.common.enums.CareerEnum;
import com.blizzard.manage.pojo.DeckCard;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-22 20:34
 */
public class DeckCardDto implements Serializable {

    private static final long serialVersionUID = -1028973543754688686L;
    
    private String name;
    private CareerEnum careerEnum;
    private List<DeckCard> deckCardList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CareerEnum getCareerEnum() {
        return careerEnum;
    }

    public void setCareerEnum(CareerEnum careerEnum) {
        this.careerEnum = careerEnum;
    }

    public List<DeckCard> getDeckCardList() {
        return deckCardList;
    }

    public void setDeckCardList(List<DeckCard> deckCardList) {
        this.deckCardList = deckCardList;
    }

    @Override
    public String toString() {
        return "DeckCardDto{" +
                "name='" + name + '\'' +
                ", careerEnum=" + careerEnum +
                ", deckCardList=" + deckCardList +
                '}';
    }
}
