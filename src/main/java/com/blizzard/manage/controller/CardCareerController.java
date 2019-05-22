package com.blizzard.manage.controller;

import com.blizzard.common.dto.ResponseDto;
import com.blizzard.manage.service.CardCareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-16 12:49
 */
@RestController("cardCareerController")
@RequestMapping("cardCareer")
public class CardCareerController {

    @Autowired
    private CardCareerService cardCareerService;

    @GetMapping("get")
    public ResponseDto<String> get(){
        return new ResponseDto<>(cardCareerService.get());
    }
}
