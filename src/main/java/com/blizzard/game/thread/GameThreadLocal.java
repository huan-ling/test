package com.blizzard.game.thread;

import com.blizzard.common.exception.BaseException;
import com.blizzard.game.pojo.Game;
import com.blizzard.game.pojo.Hero;
import com.blizzard.game.util.GameUtil;
import com.blizzard.ws.SelfTextWebSocketHandler;

import java.util.Map;

/**
 * @Description: 将游戏的常用的属性存入到线程中，方便取用
 * @Author: Huan
 * @CreateTime: 2019-03-06 15:50
 */
public class GameThreadLocal {

    private static final ThreadLocal<GameThread> GAME = new ThreadLocal<>();

    public static void set(String gameId,Integer uid){
        Hero hero = GameUtil.getHero(gameId,uid);
        Hero enemyHero = GameUtil.getAnotherHero(gameId,uid);
        GameThread gameThread = new GameThread();
        gameThread.setUid(uid);
        gameThread.setHero(hero);
        gameThread.setEnemyHero(enemyHero);
        gameThread.setEnemyUid(GameUtil.getAnotherUid(gameId,uid));
        GAME.set(gameThread);
    }

    public static Integer getHeroUid(){
        return get().getUid();
    }

    public static Hero getHero(){
        return get().getHero();
    }

    public static Integer getEnemyUid(){
        return get().getEnemyUid();
    }

    public static Hero getEnemyHero(){
        return get().getEnemyHero();
    }

    private static GameThread get(){
        GameThread gameThread = GAME.get();
        if(gameThread == null){
            throw new BaseException("游戏信息没有绑定到线程上");
        }
        return gameThread;
    }

    private static class GameThread{
        private Integer uid;
        private Hero hero;
        private Hero enemyHero;
        private Integer enemyUid;

        public Hero getHero() {
            return hero;
        }

        public void setHero(Hero hero) {
            this.hero = hero;
        }

        public Hero getEnemyHero() {
            return enemyHero;
        }

        public void setEnemyHero(Hero enemyHero) {
            this.enemyHero = enemyHero;
        }

        public Integer getUid() {
            return uid;
        }

        public void setUid(Integer uid) {
            this.uid = uid;
        }

        public Integer getEnemyUid() {
            return enemyUid;
        }

        public void setEnemyUid(Integer enemyUid) {
            this.enemyUid = enemyUid;
        }

        @Override
        public String toString() {
            return "GameThread{" +
                    "uid=" + uid +
                    ", hero=" + hero +
                    ", enemyHero=" + enemyHero +
                    ", enemyUid=" + enemyUid +
                    '}';
        }
    }
}
