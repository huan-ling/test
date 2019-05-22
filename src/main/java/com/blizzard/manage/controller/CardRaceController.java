package com.blizzard.manage.controller;

import com.blizzard.common.dto.ResponseDto;
import com.blizzard.common.enums.CardRaceEnum;
import com.blizzard.manage.service.CardRaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 卡牌种族
 * @Author: Huan
 * @CreateTime: 2019-02-16 12:27
 */
@RestController("cardRaceController")
@RequestMapping("cardRace")
public class CardRaceController {

    @Autowired
    private CardRaceService cardRaceService;

    @GetMapping("get")
    public ResponseDto<String> get(){
        return new ResponseDto<>(cardRaceService.get());
    }
}
