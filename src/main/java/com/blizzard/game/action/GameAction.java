package com.blizzard.game.action;

import com.blizzard.game.pojo.Game;

import java.util.Map;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-23 20:33
 */
public interface GameAction {

    String beginGame(String gameId, Integer uid, Map<String, Game> gameMap);

}
