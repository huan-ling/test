package com.blizzard.manage.controller;

import com.blizzard.common.dto.ResponseDto;
import com.blizzard.common.entity.Card;
import com.blizzard.common.enums.CareerEnum;
import com.blizzard.common.exception.BaseException;
import com.blizzard.manage.service.CardSerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 卡牌接口
 * @Author: Huan
 * @CreateTime: 2019-02-15 14:54
 */
@RestController("cardFacadeController")
@RequestMapping("card")
public class CardFacadeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardFacadeController.class);

    @Autowired
    private CardSerivce cardSerivce;

    @PostMapping("post")
    public ResponseDto<?> post(String ksbh, @CookieValue("validateCode")String validateCode,@CookieValue("cck_count")String count){
        LOGGER.info("ksbh="+ksbh+";validateCode="+validateCode+";count="+count);
        return new ResponseDto<>();
    }

    @PostMapping(value = "add")
    public ResponseDto<?> add(Card card){
        LOGGER.info("创建卡牌的信息是"+card);
        cardSerivce.add(card);
        return new ResponseDto<>();
    }

    @GetMapping("batchAdd")
    public ResponseDto<?> batchAdd(String url){
        LOGGER.info("爬虫链接为："+url);
        cardSerivce.batchAdd(url);
        return new ResponseDto<>();
    }

    @GetMapping("getAll")
    public ResponseDto<List<Card>> getAll(){
        return new ResponseDto<>(cardSerivce.getAll());
    }

    @GetMapping("getCareerAll")
    public ResponseDto<List<Card>> getCareerAll(CareerEnum careerEnum){
        if(careerEnum == null){
            throw new BaseException();
        }
        return new ResponseDto(cardSerivce.getCareerAll(careerEnum));
    }

}
