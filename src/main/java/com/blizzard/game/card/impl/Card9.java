package com.blizzard.game.card.impl;

import com.blizzard.common.enums.CardRaceEnum;
import com.blizzard.common.enums.ChooseObjectEnum;
import com.blizzard.common.enums.DataTypeEnum;
import com.blizzard.game.card.CardInt;
import com.blizzard.game.pojo.*;
import com.blizzard.game.util.CardSiteDtoUtil;
import com.blizzard.game.util.CardUtil;
import com.blizzard.game.util.GameUtil;
import com.blizzard.game.util.MessageUtil;
import com.blizzard.ws.entity.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: <b>战吼：</b>使一个友方机械获得+1/+1。
 * @Author: Huan
 * @CreateTime: 2019-03-02 16:53
 */
@Component
public class Card9 extends CardInt {

    @Override
    public ChooseObjectDto playPre(String gameId, Integer uid, HaveChooseObjectDto chooseDto) {
        Hero hero = GameUtil.getHero(gameId, uid);
        List<Integer> list = CardUtil.getIndexListByCardRace(hero.getSceneCardList(), CardRaceEnum.MACHINE);
        if (list != null && list.size() > 0) {
            ChooseObjectDto chooseObjectDto = new ChooseObjectDto();
            chooseObjectDto.setIndexList(list);
            chooseObjectDto.setChooseObjectEnum(ChooseObjectEnum.OTHER);
            return chooseObjectDto;
        }
        return null;
    }

    @Override
    public Message playPost(String gameId, Integer uid, HaveChooseObjectDto chooseDto) {
        Integer toSite = chooseDto.getToSite();
        Hero hero = GameUtil.getHero(gameId,uid);
        if(toSite != null){
            CardGameDto cardGameDto = GameUtil.getCardByIndex(hero.getSceneCardList(),toSite);
            cardGameDto.setBloodVolume(cardGameDto.getBloodVolume()+1);
            cardGameDto.setAttackForce(cardGameDto.getAttackForce()+1);

            CardSiteDto cardSiteDto = CardSiteDtoUtil.getCardSiteDto(toSite,cardGameDto);
            return MessageUtil.getMessage(DataTypeEnum.CARD_UPDATE,cardSiteDto);
        }
        return null;
    }
}
