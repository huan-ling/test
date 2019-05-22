package com.blizzard.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-23 18:19
 */
@Controller
@RequestMapping("game")
public class GameWebController {

    @GetMapping("{path}")
    public String getPage(@PathVariable("path") String path){
        return "/game/"+path;
    }
}


