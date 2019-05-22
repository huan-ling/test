package com.blizzard.manage.pojo;

import com.blizzard.common.enums.CareerEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 卡组
 * @Author: Huan
 * @CreateTime: 2019-02-21 15:48
 */
public class Deck implements Serializable {

    private static final long serialVersionUID = -7534432872113885029L;

    private int id;
    private String name;
    private int uid;
    private CareerEnum careerEnum;
    private Date createTime;
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public CareerEnum getCareerEnum() {
        return careerEnum;
    }

    public void setCareerEnum(CareerEnum careerEnum) {
        this.careerEnum = careerEnum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uid=" + uid +
                ", careerEnum=" + careerEnum +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
