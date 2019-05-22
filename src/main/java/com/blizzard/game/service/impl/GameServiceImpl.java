package com.blizzard.game.service.impl;

import com.blizzard.common.constant.Constant;
import com.blizzard.common.enums.DataTypeEnum;
import com.blizzard.game.card.CardInt;
import com.blizzard.game.pojo.*;
import com.blizzard.game.service.GameService;
import com.blizzard.game.thread.GameThreadLocal;
import com.blizzard.game.util.CardUtil;
import com.blizzard.game.util.GameUtil;
import com.blizzard.game.util.MessageUtil;
import com.blizzard.ws.SelfTextWebSocketHandler;
import com.blizzard.ws.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-24 15:52
 */
@Service
public class GameServiceImpl implements GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private SelfTextWebSocketHandler webSocketHandler;

    @Override
    public List<CardGameDto> choose(String gameId, Integer uid, Integer[] cardIds) {
        List<Integer> cardIdList = Arrays.asList(cardIds);
        Game game = GameUtil.getGame(gameId);
        Hero hero = GameUtil.getHero(game,uid);
        List<CardGameDto> libraryCardList = hero.getLibraryCardList();
        List<CardGameDto> handCardList = hero.getHandCardList();
        List<CardGameDto> tempList = new ArrayList<>();
        Iterator<CardGameDto> iterator = handCardList.iterator();
        while(iterator.hasNext()){
            CardGameDto cardGameDto = iterator.next();
            if(cardIdList.contains(cardGameDto.getId())){
                tempList.add(cardGameDto);
                iterator.remove();
            }
        }
        libraryCardList.addAll(tempList);
        int select = hero.isFirst() ? cardIds.length+1 : cardIds.length;
        GameUtil.handleChooseCard(hero,select);
        game.setCount(game.getCount()+1);
        if(game.getCount() >= Game.BEGIN_GAME_COUNT){
            // 通知先手玩家可以开始游戏
            game.setBoundNum(1);
            BeginRoundDto beginRoundDto = new BeginRoundDto();
            beginRoundDto.setCardGameDtoList(new ArrayList<>());
            beginRoundDto.setManaValue(1);
            beginRoundDto.setTiredValue(0);
            Integer enemyUid = GameUtil.getAnotherUid(game,uid);
            if(hero.isFirst()){
                webSocketHandler.sendMessageToUser(uid,DataTypeEnum.BEGIN_ROUND,beginRoundDto);
                // 先手 可以先抽取一张牌 upHandCount+1
                webSocketHandler.sendMessageToUser(enemyUid,DataTypeEnum.CARD_NUM_UPDATE,1);
            }else{
                webSocketHandler.sendMessageToUser(enemyUid,DataTypeEnum.BEGIN_ROUND,beginRoundDto);
                // 先手 可以先抽取一张牌
                webSocketHandler.sendMessageToUser(uid,MessageUtil.getMessage(DataTypeEnum.CARD_NUM_UPDATE,1));
            }
        }
        return hero.getHandCardList();
    }

    @Override
    public RoundDto endRound(String gameId, Integer uid) {
        Game game = GameUtil.getGame(gameId);
        Hero hero = GameUtil.getHero(game, uid);
        hero.setFirst(false);
        if (!hero.isFirst()) {
            int boundNum = game.getBoundNum()+1;
            game.setBoundNum(boundNum);
        }

        Hero anotherHero = GameUtil.getAnotherHero(game, uid);
        anotherHero.setFirst(true);
        anotherHero.getSceneCardList().forEach(card -> card.setCanAttack(1));
        List<CardGameDto> cardGameDtoList = GameUtil.handleChooseCard(anotherHero, 1);

        BeginRoundDto beginRoundDto = new BeginRoundDto();
        anotherHero.setManaValue(anotherHero.getManaValue() == Constant.MAX_MANA ? Constant.MAX_MANA : anotherHero.getManaValue()+1);
        anotherHero.setCurMana(anotherHero.getManaValue()+anotherHero.getNextMana());
        // 清零
        anotherHero.setNextMana(0);
        beginRoundDto.setManaValue(anotherHero.getCurMana());
        beginRoundDto.setTiredValue(anotherHero.getTiredValue());
        beginRoundDto.setCardGameDtoList(cardGameDtoList);

        // 通过ws通知另一个人开始回合
        webSocketHandler.sendMessageToUser(anotherHero.getUid(),DataTypeEnum.BEGIN_ROUND,beginRoundDto);

        // 返回回合信息---告知部分信息更新页面
        RoundDto roundDto = new RoundDto();
        roundDto.setDrawCardNum(cardGameDtoList.size());
        roundDto.setTiredValue(anotherHero.getTiredValue());
        roundDto.setManaValue(anotherHero.getCurMana());
        return roundDto;
    }

    @Override
    public Message play(String gameId, Integer uid, HaveChooseObjectDto chooseDto) {
        CardInt cardInt = CardUtil.getCardInt(chooseDto.getCardId());
        ChooseObjectDto chooseObjectDto = cardInt.playPre(gameId, uid, chooseDto);
        if(chooseObjectDto == null){
            return cardInt.playPost(gameId,uid,chooseDto);
        }else{
            Message message = new Message();
            message.setData(chooseObjectDto);
            message.setDataTypeEnum(DataTypeEnum.CHOOSE_CARD);
            return message;
        }
    }

    @Override
    public Message playPost(String gameId, Integer uid, HaveChooseObjectDto chooseDto) {
        Message message = CardUtil.getCardInt(chooseDto.getCardId()).playPost(gameId,uid,chooseDto);
        return message;
    }

    @Override
    public CardNumDto getCardNum(String gameId, Integer uid) {
        CardNumDto cardNumDto = new CardNumDto();
        Game game = GameUtil.getGame(gameId);
        Hero hero = GameUtil.getHero(game, uid);
        Hero anotherHero = GameUtil.getAnotherHero(game, uid);
        cardNumDto.setUpHandNum(anotherHero.getHandCardList().size());
        cardNumDto.setUpLibraryNum(anotherHero.getLibraryCardList().size());
        cardNumDto.setDownLibararyNum(hero.getLibraryCardList().size());
        return cardNumDto;
    }

    @Override
    public Message attack(AttackDto attackDto, String gameId, Integer uid) {
        Hero hero = GameThreadLocal.getHero();
        Integer attackSite = attackDto.getAttackSite();
        List<CardGameDto> sceneCardList = hero.getSceneCardList();
        CardGameDto cardGameDto = GameUtil.getCardByIndex(sceneCardList, attackDto.getSceneSite());
        Hero enemyHero = GameThreadLocal.getEnemyHero();
        int attack = cardGameDto.getAttackForce();
        if(attackSite >= 0){//敌方随从
            CardGameDto enemyCardDto = GameUtil.getCardByIndex(enemyHero.getSceneCardList(),attackSite);
            int enemyAttack = enemyCardDto.getAttackForce();
            handleAttackResult(cardGameDto,enemyAttack,false,attackDto.getSceneSite());
            handleAttackResult(enemyCardDto,attack,true,attackDto.getAttackSite());
        }else{// 攻击敌方英雄
            handleAttackHero(enemyHero,attack);
        }
        cardGameDto.setCanAttack(CardGameDto.NOT_ATTACK);
        return null;
    }

    /**
     * 处理卡牌攻击的结果   亡语
     *
     * @param cardGameDto
     * @param attackForce 攻击力
     * @param isEnemy 是否是敌方随从
     * @param site cardGameDto 在场上的位置
     */
    private void handleAttackResult(CardGameDto cardGameDto,int attackForce,boolean isEnemy,int site){
        cardGameDto.setBloodVolume(cardGameDto.getBloodVolume()-attackForce);
        if(cardGameDto.getBloodVolume() <= 0){
            // 有亡语触发亡语 并移除该卡牌 TODO
            CardInt cardInt = CardUtil.getCardInt(cardGameDto.getId());
            Hero hero = isEnemy ? GameThreadLocal.getEnemyHero() : GameThreadLocal.getHero();
            GameUtil.removeByIndex(hero.getSceneCardList(),site);
        }
        Message message = new Message();
        CardSiteDto cardSiteDto = new CardSiteDto();
        //
        cardSiteDto.setSite(isEnemy ? -site-3 : site);
        cardSiteDto.setCardGameDto(cardGameDto);
        message.setData(cardSiteDto);
        message.setDataTypeEnum(DataTypeEnum.CARD_UPDATE);
        webSocketHandler.sendMessageToUser(message);
    }

    /**
     * 攻击敌方英雄
     * @param hero
     * @param attack
     */
    private void handleAttackHero(Hero hero,int attack){
        int armor = hero.getArmor();
        int restValue = armor - attack;
        hero.setArmor(restValue >= 0 ? restValue : 0);
        if(restValue < 0){
            hero.setBloodVolume(hero.getBloodVolume() + restValue);
            if(hero.getBloodVolume() <= 0){
                // 结束游戏 TODO
                return;
            }
            HeroInfoDto heroInfoDto = new HeroInfoDto();
            heroInfoDto.setArmor(hero.getArmor());
            heroInfoDto.setBlood(hero.getBloodVolume());
            webSocketHandler.sendMessageToUser(MessageUtil.getMessage(DataTypeEnum.HERO_INFO,heroInfoDto));
        }
    }

    private void endGame(String gameId){

    }
}
