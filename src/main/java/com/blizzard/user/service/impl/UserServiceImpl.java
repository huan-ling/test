package com.blizzard.user.service.impl;

import com.blizzard.common.jedis.RedisService;
import com.blizzard.common.util.JsonUtil;
import com.blizzard.common.util.UUIDUtil;
import com.blizzard.user.mapper.UserMapper;
import com.blizzard.user.pojo.User;
import com.blizzard.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-20 13:39
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public boolean login(User user,HttpServletResponse response) {
        Integer uid = userMapper.getIdByUser(user);
        boolean result = uid != null;
        if(result){
            user.setId(uid);
            String token = UUIDUtil.getTaskName();
            redisService.set(token,JsonUtil.obj2Json(user),3600*24);
            Cookie cookie = new Cookie("token",token);
            cookie.setPath("/");
            Cookie userIdCookie = new Cookie("uid",String.valueOf(uid));
            userIdCookie.setPath("/");
            response.addCookie(cookie);
            response.addCookie(userIdCookie);
        }
        return result;
    }


}
