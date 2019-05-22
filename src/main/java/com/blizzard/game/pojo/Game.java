package com.blizzard.game.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-23 19:14
 */
public class Game implements Serializable,Cloneable{

    private static final long serialVersionUID = 6564811112235956270L;
    /**
     * 配合count使用
     */
    public static final int BEGIN_GAME_COUNT = 2;

    /**
     * 游戏id
     */
    private String gameId;
    /**
     * 回合数
     */
    private int boundNum;
    /**
     * 游戏的双方英雄 Integer 玩家uid
     */
    private Map<Integer,Hero> heroMap;

    /**
     * 计数 计算初次玩家选完牌的数量；count=2 表示双方都已经选完牌，可以开始游戏
     */
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getBoundNum() {
        return boundNum;
    }

    public void setBoundNum(int boundNum) {
        this.boundNum = boundNum;
    }

    public Map<Integer, Hero> getHeroMap() {
        return heroMap;
    }

    public void setHeroMap(Map<Integer, Hero> heroMap) {
        this.heroMap = heroMap;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + gameId + '\'' +
                ", boundNum=" + boundNum +
                ", heroMap=" + heroMap +
                ", count=" + count +
                '}';
    }

    @Override
    public Game clone() throws CloneNotSupportedException {
        Game game = (Game)super.clone();
        if(game.heroMap != null){
            game.heroMap = (Map<Integer, Hero>) ((HashMap)heroMap).clone();
            Map<Integer,Hero> map = game.heroMap;
            for(Map.Entry<Integer,Hero> entry: map.entrySet()){
                map.put(entry.getKey(),(Hero) map.get(entry.getKey()).clone());
            }
        }
        return game;
    }
}
