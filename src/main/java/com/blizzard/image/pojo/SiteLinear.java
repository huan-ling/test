package com.blizzard.image.pojo;

import java.io.Serializable;

/**
 * 点在直线上相关数据的封装
 *
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-04-18 16:31
 */
public class SiteLinear implements Serializable {
    private static final long serialVersionUID = 8713043325114082135L;
    private Site site;
    private Linear linear;
    private double distance;
    /**
     * 直线符合度 由r2和distance两项共同影响
     */
    private double conformity;

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getConformity() {
        return conformity;
    }

    public void setConformity(double conformity) {
        this.conformity = conformity;
    }

    public Linear getLinear() {
        return linear;
    }

    public void setLinear(Linear linear) {
        this.linear = linear;
    }

    @Override
    public String toString() {
        return "SiteLinear{" +
                "site=" + site +
                ", linear=" + linear +
                ", distance=" + distance +
                ", conformity=" + conformity +
                '}';
    }
}
