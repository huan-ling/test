package com.blizzard.image.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 直线相关数据的保存
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-04-24 17:30
 */
public class SiteListLinear implements Serializable {

    private static final long serialVersionUID = -1557411491438843704L;
    private List<Site> siteList;
    private Linear linear;

    public List<Site> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<Site> siteList) {
        this.siteList = siteList;
    }

    public Linear getLinear() {
        return linear;
    }

    public void setLinear(Linear linear) {
        this.linear = linear;
    }

    @Override
    public String toString() {
        return "SiteListLinear{" +
                "siteList=" + siteList +
                ", linear=" + linear +
                '}';
    }
}
