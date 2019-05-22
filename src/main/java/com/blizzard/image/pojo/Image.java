package com.blizzard.image.pojo;

import com.blizzard.common.util.IntegerUtil;
import com.blizzard.image.util.ImageConstant;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-04-16 16:17
 */
public class Image implements Serializable {

    private static final long serialVersionUID = -8519505093193978802L;
    private int height;
    private int width;
    private int[][] content;

    public Image(String content){
        String[] split = content.split(ImageConstant.SEPARATOR);
        width = IntegerUtil.getInt(split[0]);
        height = IntegerUtil.getInt(split[1]);
        this.content = new int[width][height];
        content = split[2];
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                this.content[i][j] = getChar2Int(content,width,j,i);
            }
        }
    }

    private int getChar2Int(String content,int width,int x,int y){
        char c = content.charAt(y * width + x);
        return Integer.valueOf(c+"");
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int[][] getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Image{" +
                "height=" + height +
                ", width=" + width +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
