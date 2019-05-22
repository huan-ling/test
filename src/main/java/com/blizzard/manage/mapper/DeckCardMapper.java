package com.blizzard.manage.mapper;

import com.blizzard.game.pojo.CardGameDto;
import com.blizzard.manage.pojo.DeckCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-21 17:56
 */
public interface DeckCardMapper {

    void batchInsert(@Param("deckId") int deckId,
                     @Param("deckCardList") List<DeckCard> deckCardList);

    void deleteByDId(Integer deckId);

    List<CardGameDto> getCardInfoByDId(int deckId);
}
