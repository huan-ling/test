package com.blizzard.image.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-03-10 20:58
 */
@Controller
@RequestMapping("image")
public class ImageWebController {

    @GetMapping("{path}")
    public String getPage(@PathVariable("path") String path){
        return "/image/"+path;
    }
}
