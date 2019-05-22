package com.blizzard.image.pojo;

import java.io.Serializable;

/**
 * 线性评价体系
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-04-18 16:26
 */
public class Linear implements Serializable {

    private static final long serialVersionUID = 4039081627539002763L;
    private double r2;

    private Double k;

    private Double b;

    public Double getY(double x){
        // 斜率不存在的时候
        if(k == null){
            return null;
        }
        return k*x+b;
    }

    public double getR2() {
        return r2;
    }

    public void setR2(double r2) {
        this.r2 = r2;
    }

    public Double getK() {
        return k;
    }

    public void setK(Double k) {
        this.k = k;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Linear{" +
                "r2=" + r2 +
                ", k=" + k +
                ", b=" + b +
                '}';
    }
}
