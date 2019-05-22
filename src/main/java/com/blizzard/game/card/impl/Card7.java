package com.blizzard.game.card.impl;

import com.blizzard.game.card.CardInt;
import com.blizzard.game.card.IgnoreMana;
import com.blizzard.game.pojo.ChooseObjectDto;
import com.blizzard.game.pojo.HaveChooseObjectDto;
import com.blizzard.game.pojo.Hero;
import com.blizzard.game.util.GameUtil;
import com.blizzard.ws.entity.Message;
import org.springframework.stereotype.Component;

/**
 * @Description: 每个玩家获得两个法力水晶。
 * @Author: Huan
 * @CreateTime: 2019-02-27 20:39
 */
@Component
public class Card7 extends CardInt {

    @Override
    public ChooseObjectDto playPre(String gameId, Integer uid, HaveChooseObjectDto chooseDto) {
        return null;
    }

    private void handle(Hero hero){
        int manaValue = hero.getManaValue()+2;
        int curMana = hero.getCurMana()+2;
        hero.setManaValue(manaValue>10 ? 10 : manaValue);
        hero.setCurMana(curMana>10 ? 10 : curMana);
    }

    @Override
    @IgnoreMana
    public Message playPost(String gameId, Integer uid, HaveChooseObjectDto chooseDto) {
        Hero hero = GameUtil.getHero(gameId,uid);
        Hero anthoerHero = GameUtil.getAnotherHero(gameId,uid);
        handle(hero);
        handle(anthoerHero);
        return null;
    }
}
