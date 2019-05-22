package com.blizzard.game.card;

import com.blizzard.game.pojo.ChooseObjectDto;
import com.blizzard.game.pojo.HaveChooseObjectDto;
import com.blizzard.ws.entity.Message;
import org.springframework.stereotype.Component;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-27 16:15
 */
@Component("cardInt")
public class CardInt {

    /**
     * 出牌之前调用该方法；
     * 如果没有需要选择的作用对象，则直接产生卡牌的效果
     * 如果有需要选择的作用对象，则返回需要选择的对用对象范围，卡牌的效果则会在playPost方法中体现
     *
     * @param gameId
     * @param uid
     * @param chooseDto
     * @return 需要选择的对用对象范围
     */
    public ChooseObjectDto playPre(String gameId, Integer uid, HaveChooseObjectDto chooseDto){
        return null;
    }

    /**
     * 如果需要选择对用对象则调用该方法，产生卡牌打出的效果
     *
     * @param gameId
     * @param uid
     * @param chooseDto
     */
    public Message playPost(String gameId, Integer uid, HaveChooseObjectDto chooseDto){
        return null;
    }

    /**
     * 处理卡牌的亡语
     */
    public void handleDeathRatle(){

    }

}
