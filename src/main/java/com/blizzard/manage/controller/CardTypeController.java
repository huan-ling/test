package com.blizzard.manage.controller;

import com.blizzard.common.dto.ResponseDto;
import com.blizzard.common.enums.CardTypeEnum;
import com.blizzard.manage.service.CardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 卡牌类型
 * @Author: Huan
 * @CreateTime: 2019-02-15 15:41
 */
@RestController("cardTypeController")
@RequestMapping("cardType")
public class CardTypeController {

    @Autowired
    private CardTypeService cardTypeService;

    @GetMapping("get")
    public ResponseDto<String> get(){
        return new ResponseDto<>(cardTypeService.get());
    }
}
