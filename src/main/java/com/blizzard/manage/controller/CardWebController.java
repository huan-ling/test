package com.blizzard.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 卡牌web页面的控制层
 * @Author: Huan
 * @CreateTime: 2019-02-15 15:02
 */
@Controller("CardWebController")
@RequestMapping("card")
public class CardWebController {


    @GetMapping("{path}")
    public String path(@PathVariable("path")String path){
        return "card/"+path;
    }


}
