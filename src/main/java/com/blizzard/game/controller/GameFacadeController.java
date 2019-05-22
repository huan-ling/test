package com.blizzard.game.controller;

import com.blizzard.common.dto.ResponseDto;
import com.blizzard.common.util.Assert;
import com.blizzard.game.card.impl.Card5;
import com.blizzard.game.pojo.*;
import com.blizzard.game.service.GameService;
import com.blizzard.game.thread.GameThreadLocal;
import com.blizzard.ws.entity.Message;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-24 15:47
 */
@RestController
@RequestMapping("gameFacade")
public class GameFacadeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameFacadeController.class);

    @Autowired
    private GameService gameService;

    /**
     * 选择初始卡牌
     *
     * @param gameId
     * @param uid
     * @param cardIds
     * @return
     */
    @PostMapping("choose")
    public ResponseDto<List<CardGameDto>> choose(@CookieValue("gameId") String gameId,@CookieValue("uid")Integer uid, Integer[] cardIds){
        Assert.checkNull(gameId,uid,cardIds);
        LOGGER.info("【参数】uid={};gameId={};carIds={}",gameId,uid,cardIds);
        List<CardGameDto> list =  gameService.choose(gameId,uid,cardIds);
        return new ResponseDto<>(list);
    }

    /**
     * 结束回合
     * @return
     */
    @PostMapping("endRound")
    public ResponseDto<RoundDto> endRound(@CookieValue("gameId") String gameId, @CookieValue("uid")Integer uid){
        Assert.checkNull(gameId,uid);
        return new ResponseDto<>(gameService.endRound(gameId,uid));
    }

    /**
     * 打出一张牌
     * 如果是具有 作用对象的牌返回对用对象
     * @return
     */
    @PostMapping("play")
    public ResponseDto<Message> play(HaveChooseObjectDto chooseDto, @CookieValue("gameId") String gameId, @CookieValue("uid")Integer uid){
        Assert.checkNull(chooseDto,gameId,uid);
        GameThreadLocal.set(gameId,uid);
        return new ResponseDto<>(gameService.play(gameId,uid,chooseDto));
    }

    @PostMapping("playPost")
    public ResponseDto<Message> playPost(HaveChooseObjectDto chooseDto, @CookieValue("gameId")String gameId, @CookieValue("uid")Integer uid){
        Assert.checkNull(chooseDto,gameId,uid);
        GameThreadLocal.set(gameId,uid);
        return new ResponseDto<>(gameService.playPost(gameId,uid,chooseDto));
    }

    @GetMapping("getCardNum")
    public ResponseDto<CardNumDto> getCardNum(@CookieValue("gameId") String gameId, @CookieValue("uid")Integer uid){
        Assert.checkNull(gameId,uid);
        return new ResponseDto<>(gameService.getCardNum(gameId,uid));
    }

    @PostMapping("attack")
    public ResponseDto<Message> attack(AttackDto attackDto,@CookieValue("gameId")String gameId,@CookieValue("uid")Integer uid){
        Assert.checkNull(attackDto,gameId,uid);
        GameThreadLocal.set(gameId,uid);
        return new ResponseDto<>(gameService.attack(attackDto,gameId,uid));
    }
}
