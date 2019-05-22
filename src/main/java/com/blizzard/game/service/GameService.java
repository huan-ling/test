package com.blizzard.game.service;

import com.blizzard.game.pojo.*;
import com.blizzard.ws.entity.Message;

import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-24 15:52
 */
public interface GameService {
    List<CardGameDto> choose(String gameId, Integer uid, Integer[] cardIds);

    /**
     * 回合结束
     *
     * @param gameId
     * @param uid
     * @return
     */
    RoundDto endRound(String gameId, Integer uid);

    Message play(String gameId, Integer uid, HaveChooseObjectDto chooseDto);

    Message playPost(String gameId, Integer uid, HaveChooseObjectDto chooseDto);

    /**
     * 获取卡牌的数量
     *
     * @param gameId
     * @param uid
     * @return
     */
    CardNumDto getCardNum(String gameId, Integer uid);

    /**
     * 攻击
     *
     * @param attackDto
     * @param gameId
     * @param uid
     * @return
     */
    Message attack(AttackDto attackDto, String gameId, Integer uid);
}
