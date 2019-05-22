package com.blizzard.game.util;

import com.blizzard.common.exception.BaseException;
import com.blizzard.common.util.RandomUtil;
import com.blizzard.game.pojo.CardGameDto;
import com.blizzard.game.pojo.Game;
import com.blizzard.game.pojo.Hero;
import com.blizzard.ws.SelfTextWebSocketHandler;

import java.util.*;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-24 16:22
 */
public class GameUtil {
    /**
     * 从牌库取出select个放到手牌中
     *
     * @param hero
     * @param select
     * @return 返回抽取牌库的牌
     */
    public static List<CardGameDto> handleChooseCard(Hero hero,int select){
        if(select <= 0){
            return new ArrayList<>();
        }
        List<CardGameDto> libraryCardList = hero.getLibraryCardList();
        if(select > libraryCardList.size()){
            int tired = hero.getTiredValue()+select-libraryCardList.size();
            if(hero.getBloodVolume() < tired){
                // TODO GAME OVER
            }
            hero.setTiredValue(tired);
        }
        if(libraryCardList.size() == 0){
            return new ArrayList<>();
        }
        List<CardGameDto> selectList = new ArrayList<>();
        List<Integer> indexList = RandomUtil.getIntArr(libraryCardList.size(), select);
        // 从大到小排序
        Collections.sort(indexList,(num1, num2) -> num2-num1);
        List<CardGameDto> handCardList;
        if(hero.getHandCardList() == null){
            handCardList = new ArrayList<>();
        }else{
            handCardList = hero.getHandCardList();
        }
        for(Integer index: indexList){
            CardGameDto cardGameDto = libraryCardList.get(index);
            selectList.add(cardGameDto);
            libraryCardList.remove(Integer.parseInt(String.valueOf(index)));
        }
        handCardList.addAll(selectList);
        hero.setHandCardList(handCardList);
        return selectList;
    }

    public static Game getGame(String gameId){
        Game game = SelfTextWebSocketHandler.getGameMap().get(gameId);
        if(game == null){
            throw new BaseException(100,"游戏不存在，gameId="+gameId);
        }
        return game;
    }

    public static Hero getHero(Game game,Integer uid){
        Hero hero = game.getHeroMap().get(uid);
        if(hero == null){
            throw new BaseException(100,"英雄不存在,game="+game+";uid="+uid);
        }
        return hero;
    }

    public static Hero getHero(String gameId,Integer uid){
        return getHero(getGame(gameId),uid);
    }

    public static List<CardGameDto> getScene(String gameId,Integer uid){
        return getHero(gameId,uid).getSceneCardList();
    }

    private static void checkIndex(List<CardGameDto> cardGameDtoList,Integer index){
        if(index > cardGameDtoList.size()){
            throw new BaseException(100,"卡牌的所在位置的下表越界，index="+index);
        }
    }

    /**
     *
     * @param cardGameDtoList
     * @param index 要获取的元素在集合中的位置
     * @return
     */
    public static CardGameDto getCardByIndex(List<CardGameDto> cardGameDtoList,Integer index){
        checkIndex(cardGameDtoList,index);
        return cardGameDtoList.get(index);
    }

   public static CardGameDto getCardAndRemoveByIndex(List<CardGameDto> cardGameDtoList,Integer index){
        CardGameDto cardGameDto = getCardByIndex(cardGameDtoList,index);
        cardGameDtoList.remove(Integer.parseInt(String.valueOf(index)));
        return cardGameDto;
   }

   public static void removeByIndex(List<CardGameDto> cardGameDtoList,Integer index){
        checkIndex(cardGameDtoList,index);
        cardGameDtoList.remove(Integer.parseInt(String.valueOf(index)));
   }



    /**
     * 获得另一个人的id
     *
     * @return
     */
    public static Integer getAnotherUid(Game game, Integer uid){
        Map<Integer, Hero> heroMap = game.getHeroMap();
        for(Integer id : heroMap.keySet()){
            if(!id.equals(uid)){
                return id;
            }
        }
        throw new BaseException(100,"玩家不存在，通过uid寻求另一个玩家，uid="+uid);
    }

    public static Integer getAnotherUid(String gameId,Integer uid){
        return getAnotherUid(getGame(gameId),uid);
    }

    /**
     * 获得另一个玩家
     *
     * @param game
     * @param uid
     * @return
     */
    public static Hero getAnotherHero(Game game,Integer uid){
        for(Map.Entry<Integer,Hero> entry : game.getHeroMap().entrySet()){
            if(!entry.getKey().equals(uid)){
                return entry.getValue();
            }
        }
        throw new BaseException(100,"玩家不存在，通过uid寻求另一个玩家，uid="+uid);
    }

    public static Hero getAnotherHero(String gameId,Integer uid){
        return getAnotherHero(getGame(gameId),uid);
    }

    public static List<CardGameDto> getAnotherScene(String gameId,Integer uid){
        return getAnotherHero(gameId,uid).getSceneCardList();
    }
}
