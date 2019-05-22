package com.blizzard.game.card.impl;

import com.blizzard.common.enums.ChooseObjectEnum;
import com.blizzard.game.card.CardInt;
import com.blizzard.game.card.IgnoreMana;
import com.blizzard.game.pojo.CardGameDto;
import com.blizzard.game.pojo.ChooseObjectDto;
import com.blizzard.game.pojo.HaveChooseObjectDto;
import com.blizzard.game.pojo.Hero;
import com.blizzard.game.util.ChooseObjectUtil;
import com.blizzard.game.util.GameUtil;
import com.blizzard.ws.entity.Message;
import org.springframework.stereotype.Component;

/**
 * 使一个随从的攻击力和生命值 互换。
 *
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-27 19:19
 */
@Component
public class Card6 extends CardInt {

    @Override
    @IgnoreMana
    public ChooseObjectDto playPre(String gameId, Integer uid, HaveChooseObjectDto chooseDto) {
        return ChooseObjectUtil.get(ChooseObjectEnum.RETINUE);
    }

    @Override
    public Message playPost(String gameId, Integer uid, HaveChooseObjectDto chooseDto) {
        ChooseObjectEnum chooseObjectEnum = chooseDto.getChooseObjectEnum();
        CardGameDto cardGameDto = null;
        if(chooseObjectEnum.equals(ChooseObjectEnum.ENEMY_RETINUE)){
            Hero anthoerHero = GameUtil.getAnotherHero(gameId,uid);
            cardGameDto = GameUtil.getCardByIndex(anthoerHero.getSceneCardList(),chooseDto.getToSite());
        }else{
            Hero hero = GameUtil.getHero(gameId,uid);
            cardGameDto = GameUtil.getCardByIndex(hero.getSceneCardList(),chooseDto.getToSite());
        }
        int attack = cardGameDto.getAttackForce();
        cardGameDto.setAttackForce(cardGameDto.getBloodVolume());
        cardGameDto.setBloodVolume(attack);
        return null;
    }

}
