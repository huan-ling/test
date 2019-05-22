package com.blizzard.game.action;

import com.blizzard.game.pojo.Game;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-23 19:31
 */
public interface PrepareGameAction {

    /**
     *
     * @param uid
     * @param deckId
     * @param matchId
     * @param matchDeckId
     * @return
     */
    Game parepareGame(int uid, int deckId, int matchId, int matchDeckId);
}
