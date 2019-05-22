package com.blizzard.game.util;

import com.blizzard.common.enums.DataTypeEnum;
import com.blizzard.ws.entity.Message;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-03-02 17:53
 */
public class MessageUtil {

    public static <T> Message<T> getMessage(DataTypeEnum dataTypeEnum,T t){
        Message<T> message = new Message();
        message.setDataTypeEnum(dataTypeEnum);
        message.setData(t);
        return message;
    }
}
