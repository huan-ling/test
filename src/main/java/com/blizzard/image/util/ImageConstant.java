package com.blizzard.image.util;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-04-15 18:49
 */
public interface ImageConstant {

    /**
     * 图片的基础路径
     */
    String BASE_URL = "F://image/";

    /**
     * 图片的后缀
     */
    String SUFFIX = ".image";

    /**
     * 图片中的分隔符 ;
     */
    String SEPARATOR = ";";

    /**
     * 表示图片中含有的内容
     */
    int SOLID = 1;

    /**
     * 精度值 2
     */
    int PRECISION = 2;

    /**
     *
     */
    double R2 = 0.85;

    /**
     * 线性分析最少直线点数
     */
    int LINEAR_POINT_COUNT = 5;
}
