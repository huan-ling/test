package com.blizzard.user.controller;

import com.blizzard.common.exception.BaseException;
import com.blizzard.user.pojo.User;
import com.blizzard.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-20 13:35
 */
@Controller
@RequestMapping("user")
public class UserWebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserWebController.class);

    @Autowired
    private UserService userService;

    @GetMapping("{path}")
    public String getPage(@PathVariable("path") String path){
        LOGGER.info("请求路径为{}",path);
        return "user/"+path;
    }


    @PostMapping("login")
    public String login(User user, HttpServletResponse response){
        if(userService.login(user,response)){
            return "redirect:/card/index.html";
        }else {
            return "user/login";
        }
    }
}
