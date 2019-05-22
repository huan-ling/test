package com.blizzard.game.action.impl;

import com.blizzard.common.exception.BaseException;
import com.blizzard.common.util.RandomUtil;
import com.blizzard.common.util.UUIDUtil;
import com.blizzard.game.action.PrepareGameAction;
import com.blizzard.game.pojo.CardGameDto;
import com.blizzard.game.pojo.Game;
import com.blizzard.game.pojo.Hero;
import com.blizzard.game.util.GameUtil;
import com.blizzard.manage.mapper.DeckCardMapper;
import com.blizzard.manage.mapper.DeckMapper;
import com.blizzard.manage.pojo.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-23 19:32
 */
@Component
public class PrepareGameActionImpl implements PrepareGameAction {

    @Autowired
    private DeckMapper deckMapper;
    @Autowired
    private DeckCardMapper deckCardMapper;

    @Override
    public Game parepareGame(int uid, int deckId, int matchId, int matchDeckId) {
        Game game = new Game();
        game.setGameId(UUIDUtil.getTaskName());
        // 0表示未开始回合
        game.setBoundNum(0);
        Map<Integer,Hero> heroMap = new HashMap<>();
        Hero hero1 = createHero(uid, deckId);
        Hero hero2 = createHero(matchId,matchDeckId);
        if(RandomUtil.getInt(2) == 1){
            setHero(hero1,hero2);
        }else {
            setHero(hero2,hero1);
        }
        heroMap.put(uid,hero1);
        heroMap.put(matchId,hero2);
        game.setHeroMap(heroMap);
        return game;
    }

    private void setHero(Hero firstHero,Hero laterHero){
        firstHero.setFirst(true);
        firstHero.setCurMana(1);
        firstHero.setManaValue(1);
        GameUtil.handleChooseCard(firstHero,3);
        GameUtil.handleChooseCard(laterHero,4);
    }


    private Hero createHero(int uid,int deckId){
        Hero hero = new Hero();
        Deck deck = deckMapper.getByDId(deckId);
        if(deck == null){
            throw new BaseException(100,"无法查找课表deckId="+deckId);
        }
        hero.setBloodVolume(30);
        hero.setUid(uid);
        hero.setSceneCardList(new ArrayList<>());
        hero.setCareerEnum(deck.getCareerEnum());
        List<CardGameDto> deckCardList = deckCardMapper.getCardInfoByDId(deckId);
        List<CardGameDto> libaryCardList = new ArrayList<>();
        for(CardGameDto cardGameDto : deckCardList){
            if(cardGameDto.getNum() == 2){
                libaryCardList.add(cardGameDto);
            }
            libaryCardList.add(cardGameDto);
        }
        hero.setLibraryCardList(libaryCardList);
        return hero;
    }
}
