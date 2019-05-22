package com.blizzard.manage.mapper;

import com.blizzard.common.enums.CareerEnum;
import com.blizzard.manage.pojo.Deck;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-21 16:05
 */
public interface DeckMapper {

    List<Deck> getAllByUId(Integer uid);

    void insert(Deck deck);

    int deleteByIdAndUId(@Param("deckId") Integer deckId, @Param("uid") Integer uid);

    Deck getByDId(int deckId);
}
