package com.blizzard.image.util;

import com.blizzard.common.exception.BaseException;
import com.blizzard.common.util.JsonUtil;
import com.blizzard.image.pojo.Image;
import com.blizzard.image.pojo.Linear;
import com.blizzard.image.pojo.Site;
import com.blizzard.image.pojo.SiteLinear;
import com.blizzard.image.pojo.SiteListLinear;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-04-16 16:10
 */
public class ImageAnalysis {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageAnalysis.class);

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        String path = "F://image/52629/79fd4a7356bf4c7a8a5743cd8e6bad03.image";
        String content = ImageFileUtil.readFile2Image(path);
        LOGGER.info("{}", content);
        Image image = new Image(content);

        long time2 = System.currentTimeMillis();

        analysis(image);
        LOGGER.info("耗时{}毫秒",time2 - time1);
    }

    public static void analysis(Image image) {
        int[][] content = image.getContent();
        Site firstSite = getFirstSite(content);
        if (firstSite == null) {
            throw new BaseException("图片内容为空");
        }
        List<SiteListLinear> siteListAll = new ArrayList<>();
        List<Site> siteList = new ArrayList<>();

        siteList.add(firstSite);
        SiteLinear lastSite = null;
        SiteLinear nextSite;
        while ((nextSite = getNextSite(content, siteList)) != null) {
            LOGGER.info("{}******",nextSite);
            if (siteList.size() >= ImageConstant.LINEAR_POINT_COUNT) {
                List<Site> linearList = siteList.subList(siteList.size() - ImageConstant.LINEAR_POINT_COUNT, siteList.size());
                linearList = new ArrayList<>(linearList);
                linearList.add(nextSite.getSite());
                Linear linear = LinearRegression.isLinear(linearList);
                LOGGER.info("{}===========",linear);
                if(linear.getR2() < ImageConstant.R2){
                    siteListAll.add(getSiteListLinear(siteList,lastSite));
                    removeList(content,siteList);
                    siteList = new ArrayList<>();
                }
            }
            siteList.add(nextSite.getSite());
            lastSite = nextSite;
        }
        if(lastSite == null){
            return;
        }
        siteListAll.add(getSiteListLinear(siteList,lastSite));
        LOGGER.info("{}", JsonUtil.obj2Json(siteListAll));

        // 分析角度问题
        LinearAnalysis.analysis(siteListAll);
    }


    public static Site getFirstSite(int[][] content) {
        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[i].length; j++) {
                if (content[i][j] == ImageConstant.SOLID) {
                    Site site = new Site();
                    site.setX(j);
                    site.setY(i);
                    return site;
                }
            }
        }
        return null;
    }

    public static SiteLinear getNextSite(int[][] content, List<Site> siteList) {

        Site lastSite = siteList.get(siteList.size() - 1);

        int minusLimit = (int) lastSite.getY() - ImageConstant.PRECISION < 0 ?
                -(int) lastSite.getY() : -ImageConstant.PRECISION;
        int limit = lastSite.getY() + ImageConstant.PRECISION > content.length ?
                content.length - ImageConstant.PRECISION : ImageConstant.PRECISION;

        int xMinusLimit = (int) lastSite.getX() - ImageConstant.PRECISION < 0 ?
                -(int) lastSite.getX() : -ImageConstant.PRECISION;

        List<SiteLinear> candidateSiteList = new ArrayList<>();
        for (int i = minusLimit; i <= limit; i++) {
            int xLimit = lastSite.getX() + ImageConstant.PRECISION > content[(int) lastSite.getY() + i].length ?
                    content[(int)lastSite.getY() + i].length - ImageConstant.PRECISION : ImageConstant.PRECISION;
            for (int j = xMinusLimit; j <= xLimit; j++) {
                Site nextSite = new Site();
                nextSite.setX(lastSite.getX() + j);
                nextSite.setY(lastSite.getY() + i);
                if (!siteList.contains(nextSite)
                        && isAccordPercision(lastSite, nextSite)
                        && content[(int)nextSite.getY()][(int)nextSite.getX()] == ImageConstant.SOLID) {
                    SiteLinear siteLinear = new SiteLinear();
                    siteLinear.setSite(nextSite);
                    candidateSiteList.add(siteLinear);
                }
            }
        }

        if(candidateSiteList.isEmpty()){
            return null;
        }

        Iterator<SiteLinear> iterator = candidateSiteList.iterator();
        while (iterator.hasNext()){
            SiteLinear next = iterator.next();
            List<Site> linearList = new ArrayList<>();
            linearList.addAll(siteList);
            linearList.add(next.getSite());
            Linear linear = LinearRegression.isLinear(linearList);
            next.setLinear(linear);
            next.setDistance(getPointDistance(next.getSite(),lastSite));
            next.setConformity(getConformity(next));
        }

        Collections.sort(candidateSiteList,(s1,s2)->
                (int)(s2.getConformity()*100-s1.getConformity()*100)
        );

        return candidateSiteList.get(0);
    }

    /**
     * 判断nextSite是否在精度允许范围内的点 lastSite
     *
     * @param lastSite
     * @param nextSite
     * @return
     */
    public static boolean isAccordPercision(Site lastSite, Site nextSite) {
        if (lastSite.getX() == nextSite.getX() && lastSite.getY() == nextSite.getY()) {
            return false;
        }
        double result = Math.pow(nextSite.getX() - lastSite.getX(), 2) +
                Math.pow(nextSite.getY() - lastSite.getY(), 2) -
                Math.pow(ImageConstant.PRECISION + 0.5, 2);
        return result <= 0;
    }

    private static void removeList(int[][] content,List<Site> siteList){
        for(Site site : siteList){
            content[(int) site.getY()][(int) site.getX()] = 0;
        }
    }


    private static double getPointDistance(Site one,Site another){
        double result = Math.pow(one.getX()-another.getX(),2)+Math.pow(one.getY()-another.getY(),2);
        return Math.sqrt(result);
    }

    /**
     * 获得点的符合度，该值越大表明越符合
     * @param siteLinear
     * @return
     */
    private static double getConformity(SiteLinear siteLinear){
        return siteLinear.getLinear().getR2()+1/(siteLinear.getDistance());
    }

    private static SiteListLinear getSiteListLinear(List<Site> siteList,SiteLinear siteLinear){
        SiteListLinear siteListLinear = new SiteListLinear();
        siteListLinear.setSiteList(siteList);
        siteListLinear.setLinear(siteLinear.getLinear());
        return siteListLinear;
    }

}
