package com.blizzard.image.util;

import com.blizzard.image.pojo.Linear;
import com.blizzard.image.pojo.Site;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 线性回归
 *
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-04-17 17:26
 */
public class LinearRegression {

    public static Linear isLinear(List<Site> siteList) {
        List<Double> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();
        List<Double> xyList = new ArrayList<>();
        List<Double> x2List = new ArrayList<>();
        for (Site site : siteList) {
            xList.add((double) site.getX());
            yList.add((double) site.getY());
            xyList.add((double) site.getX() * site.getY());
            x2List.add(Math.pow(site.getX(), 2));
        }
        double xAverage = getAverage(xList);
        double yAverage = getAverage(yList);
        double xyAverage = getAverage(xyList);
        double x2Average = getAverage(x2List);
        Double a = getA(xAverage, yAverage, xyAverage, x2Average);
        Double b = getB(yAverage, a, xAverage);

        Linear linear = new Linear();
        double sst = getSS(yList, yAverage);
        linear.setK(a);
        linear.setB(b);
        if(a == null || sst == 0){
            linear.setR2(1);
            return linear;
        }
        List<Double> regressionYList = getRegressionYList(a, b, xList);
        double ssr = getSS(regressionYList, yAverage);
        double r2 = getR2(ssr, sst);
        linear.setR2(r2);
        return linear;
    }

    private static double getAverage(List<Double> list) {
        int sum = 0;
        for (Double num : list) {
            sum += num;
        }
        BigDecimal bigDecimal = new BigDecimal(sum);
        return bigDecimal.divide(new BigDecimal(list.size()),10,RoundingMode.HALF_DOWN).doubleValue();
    }

    private static Double getA(double xAverage, double yAverage, double xyAverage, double x2Average) {
        BigDecimal bigDecimal = new BigDecimal(xAverage);
        bigDecimal = bigDecimal.multiply(new BigDecimal(yAverage)).subtract(new BigDecimal(xyAverage));
        BigDecimal divideBigDecimal = new BigDecimal(xAverage);
        divideBigDecimal = divideBigDecimal.pow(2).subtract(new BigDecimal(x2Average));
        if(divideBigDecimal.doubleValue() == 0){
            return null;
        }
        return bigDecimal.divide(divideBigDecimal,10,RoundingMode.HALF_DOWN).doubleValue();
    }

    private static Double getB(double yAverage, Double a, double xAverage) {
        if(a == null){
            return null;
        }
        return yAverage - a * xAverage;
    }

    private static double getSS(List<Double> list, double yAverage) {
        double ss = 0;
        for (Double num : list) {
            ss += Math.pow(num - yAverage, 2);
        }
        return ss;
    }

    private static double getReslut(Double a, double b, double x) {
        return a * x + b;
    }

    private static List<Double> getRegressionYList(double a, double b, List<Double> xList) {
        List<Double> regressionYList = new ArrayList<>();
        for (Double num : xList) {
            regressionYList.add(getReslut(a, b, num));
        }
        return regressionYList;
    }

    private static double getR2(double ssr, double sst) {
        BigDecimal bigDecimal = new BigDecimal(ssr);
        return bigDecimal.divide(new BigDecimal(sst),10,RoundingMode.HALF_DOWN).doubleValue();
    }

}
