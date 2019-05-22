package com.blizzard.game.card.impl;


import com.blizzard.game.card.CardInt;
import com.blizzard.game.card.IgnoreMana;
import com.blizzard.game.pojo.CardGameDto;
import com.blizzard.game.pojo.ChooseObjectDto;
import com.blizzard.game.pojo.HaveChooseObjectDto;
import com.blizzard.game.pojo.Hero;
import com.blizzard.game.util.GameUtil;
import com.blizzard.ws.entity.Message;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

/**
 * 对所有随从造成1点伤害。 <b>过载：</b>（2）
 *
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-27 16:17
 */
@Component
public class Card5 extends CardInt {

    @Override
    public ChooseObjectDto playPre(String gameId, Integer uid, HaveChooseObjectDto chooseDto) {
        return null;
    }

    private void handle(List<CardGameDto> cardGameDtoList){
        Iterator<CardGameDto> sceneIterator = cardGameDtoList.iterator();
        while(sceneIterator.hasNext()){
            CardGameDto cardGameDto = sceneIterator.next();
            cardGameDto.setBloodVolume(cardGameDto.getBloodVolume()-1);
            if(cardGameDto.getBloodVolume() <= 0){
                sceneIterator.remove();
            }
        }
    }

    @Override
    public Message playPost(String gameId, Integer uid, HaveChooseObjectDto chooseDto) {
        Hero hero = GameUtil.getHero(gameId, uid);
        hero.setNextMana(-2);
        List<CardGameDto> sceneList = GameUtil.getScene(gameId, uid);
        List<CardGameDto> anotherSceneList = GameUtil.getAnotherScene(gameId,uid);
        handle(sceneList);
        handle(anotherSceneList);
        return null;
    }


}
