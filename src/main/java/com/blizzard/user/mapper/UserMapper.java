package com.blizzard.user.mapper;

import com.blizzard.user.pojo.User;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-20 13:42
 */
public interface UserMapper {
    Integer getIdByUser(User user);
}
