package com.blizzard.game.card.impl;

import com.blizzard.game.card.CardInt;
import com.blizzard.game.pojo.HaveChooseObjectDto;
import com.blizzard.game.pojo.Hero;
import com.blizzard.game.util.GameUtil;
import com.blizzard.ws.entity.Message;
import org.springframework.stereotype.Component;

/**
 * 滑板机器人
 * @Description: <b>磁力</b> <b>突袭</b>
 * @Author: Huan
 * @CreateTime: 2019-03-06 13:23
 */
@Component
public class Card23 extends CardInt {

    @Override
    public Message playPost(String gameId, Integer uid, HaveChooseObjectDto chooseDto) {

        Hero hero = GameUtil.getHero(gameId,uid);
        Integer sceneSite = chooseDto.getSceneSite();

        return null;
    }
}
