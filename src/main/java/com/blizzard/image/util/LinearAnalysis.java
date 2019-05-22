package com.blizzard.image.util;

import com.blizzard.image.pojo.Linear;
import com.blizzard.image.pojo.Site;
import com.blizzard.image.pojo.SiteListLinear;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-04-24 17:40
 */
public class LinearAnalysis {

    public static void analysis(List<SiteListLinear> linearList){
        SiteListLinear first = linearList.get(0);
        Site a = getVector(first);
        List<Double> cosList = new ArrayList<>();
        for(int i=1;i<linearList.size();i++){
            SiteListLinear current = linearList.get(i);
            Site b = getVector(current);
            cosList.add(getCos(a,b));
            a = b;
        }
        System.out.println("==="+cosList);
        for(double cos : cosList){
            System.out.println(Math.toDegrees(Math.acos(cos)));
        }
    }

    private static double getCos(Site a,Site b){
        double ab = a.getX()*b.getX()+a.getY()*b.getY();
        double abModel = Math.sqrt(Math.pow(a.getX(),2)+Math.pow(a.getY(),2))*
                Math.sqrt(Math.pow(b.getX(),2)+Math.pow(b.getY(),2));
        return ab/abModel;
    }

    private static Site getVector(SiteListLinear listLinear){
        List<Site> siteList = listLinear.getSiteList();
        Site firstSite = siteList.get(0);
        Site endSite = siteList.get(siteList.size() - 1);
        Linear linear = listLinear.getLinear();
        Site site = new Site();
        site.setX(endSite.getX()-firstSite.getX());
        if(linear.getK() == null){
            site.setY(endSite.getY() - firstSite.getY());
        }else{
            site.setY(linear.getY(endSite.getX())-linear.getY(firstSite.getX()));
        }
        return site;
    }
}
