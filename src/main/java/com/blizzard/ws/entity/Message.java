package com.blizzard.ws.entity;

import com.blizzard.common.enums.DataTypeEnum;
import com.blizzard.common.util.JsonUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-19 16:37
 */
public class Message<T> implements Serializable {

    private static final long serialVersionUID = 8641181456768936283L;

    public static final Integer TO_SELF = 0;
    public static final Integer TO_ENEMY = -1;

    private int from;
    // -1 表示敌方 0 表示友方
    private int to;
    private DataTypeEnum dataTypeEnum;
    private T data;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public DataTypeEnum getDataTypeEnum() {
        return dataTypeEnum;
    }

    public void setDataTypeEnum(DataTypeEnum dataTypeEnum) {
        this.dataTypeEnum = dataTypeEnum;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from=" + from +
                ", to=" + to +
                ", dataTypeEnum=" + dataTypeEnum +
                ", data=" + data +
                '}';
    }

}
