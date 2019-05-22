package com.blizzard.manage.controller;

import com.blizzard.common.dto.DeckCardDto;
import com.blizzard.common.dto.ResponseDto;
import com.blizzard.common.enums.CareerEnum;
import com.blizzard.common.exception.BaseException;
import com.blizzard.common.util.Assert;
import com.blizzard.manage.pojo.Deck;
import com.blizzard.manage.pojo.DeckCard;
import com.blizzard.manage.service.DeckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-21 15:54
 */
@RestController
@RequestMapping("deckFacade")
public class DeckFacadeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeckFacadeController.class);

    @Autowired
    private DeckService deckService;

    @GetMapping("getAll")
    public ResponseDto<List<Deck>> getAll(@CookieValue("uid") Integer uid){
        Assert.checkUId(uid);
        return new ResponseDto<>(deckService.getAllByUId(uid));
    }

    @PostMapping("add")
    public ResponseDto<?> add(@RequestBody DeckCardDto deckCardDto, @CookieValue("uid")Integer uid){
        Assert.checkNull(deckCardDto,uid);
        deckService.add(deckCardDto.getName(),deckCardDto.getCareerEnum(),deckCardDto.getDeckCardList(),uid);
        return new ResponseDto<>();
    }

    @PostMapping("delete")
    public ResponseDto<?> delete(Integer deckId,@CookieValue("uid") Integer uid){
        Assert.checkNull(deckId,uid);
        deckService.delete(deckId,uid);
        return new ResponseDto<>();
    }
}


