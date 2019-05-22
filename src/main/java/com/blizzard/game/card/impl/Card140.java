package com.blizzard.game.card.impl;

import com.blizzard.common.enums.DataTypeEnum;
import com.blizzard.game.card.CardInt;
import com.blizzard.game.card.IgnoreMana;
import com.blizzard.game.pojo.ChooseObjectDto;
import com.blizzard.game.pojo.HaveChooseObjectDto;
import com.blizzard.game.pojo.Hero;
import com.blizzard.game.pojo.ManaDto;
import com.blizzard.game.util.GameUtil;
import com.blizzard.ws.entity.Message;
import org.springframework.stereotype.Component;

/**
 * @Description: 在本回合中，获得一个法力水晶
 * @Author: Huan
 * @CreateTime: 2019-02-27 21:12
 */
@Component
public class Card140 extends CardInt {

    @Override
    public ChooseObjectDto playPre(String gameId, Integer uid,HaveChooseObjectDto chooseDto) {
        return null;
    }

    @Override
    @IgnoreMana
    public Message playPost(String gameId, Integer uid, HaveChooseObjectDto chooseDto) {
        Hero hero = GameUtil.getHero(gameId, uid);
        int curMana = hero.getCurMana()+1;
        hero.setCurMana(curMana>10 ? 10 : curMana);

        Message message = new Message();
        message.setDataTypeEnum(DataTypeEnum.MANA);
        ManaDto manaDto = new ManaDto();
        manaDto.setMana(hero.getCurMana());
        message.setData(manaDto);
        return message;
    }
}
