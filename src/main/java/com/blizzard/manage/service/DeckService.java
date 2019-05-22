package com.blizzard.manage.service;

import com.blizzard.common.enums.CareerEnum;
import com.blizzard.manage.pojo.Deck;
import com.blizzard.manage.pojo.DeckCard;

import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-21 16:03
 */
public interface DeckService {

    List<Deck> getAllByUId(Integer uid);

    void add(String name, CareerEnum careerEnum, List<DeckCard> deckCardList, Integer uid);

    void delete(Integer deckId, Integer uid);
}
