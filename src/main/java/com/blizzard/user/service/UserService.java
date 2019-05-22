package com.blizzard.user.service;

import com.blizzard.user.pojo.User;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-20 13:38
 */
public interface UserService {
    boolean login(User user, HttpServletResponse response);
}
