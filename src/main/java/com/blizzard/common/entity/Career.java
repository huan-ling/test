package com.blizzard.common.entity;

import com.blizzard.common.enums.CareerEnum;

import java.io.Serializable;

/**
 * @Description: 职业
 * @Author: Huan
 * @CreateTime: 2019-02-15 14:46
 */
public class Career implements Serializable {

    private static final long serialVersionUID = 2931927032562399901L;
    private CareerEnum careerEnum;
    private int bloodVolume;

    public CareerEnum getCareerEnum() {
        return careerEnum;
    }

    public void setCareerEnum(CareerEnum careerEnum) {
        this.careerEnum = careerEnum;
    }

    @Override
    public String toString() {
        return "Career{" +
                "careerEnum=" + careerEnum +
                ", bloodVolume=" + bloodVolume +
                '}';
    }
}
