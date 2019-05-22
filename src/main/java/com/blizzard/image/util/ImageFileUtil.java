package com.blizzard.image.util;

import com.blizzard.common.exception.BaseException;
import com.blizzard.common.util.Assert;
import com.blizzard.common.util.StringUtil;
import com.blizzard.common.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-04-15 20:23
 */
public class ImageFileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageFileUtil.class);

    /**
     * 将图片内容存入到文件中
     *
     * @param name
     * @param content
     * @return 返回存储的绝对路径
     */
    public static String saveImage2File(String name, String content) {
        Assert.checkNull(name, content);
        String dirPath = StringUtil.jointObjectStr(ImageConstant.BASE_URL,
                name.hashCode(), "/");
        String filePath = StringUtil.jointObjectStr(dirPath, UUIDUtil.getTaskName(), ImageConstant.SUFFIX);
        File file = new File(dirPath);
        file.mkdirs();

        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(filePath));
            out.write(content.getBytes());
        } catch (FileNotFoundException e) {
            LOGGER.error("文件不存在" + filePath, e);
            throw new BaseException();
        } catch (IOException e) {
            LOGGER.error("IO异常" + filePath, e);
            throw new BaseException();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    LOGGER.error("流关闭IO异常" + filePath, e);
                } finally {
                    out = null;
                }
            }
        }
        return filePath;
    }

    public static String readFile2Image(String path) {
        InputStreamReader isr = null;
        String content = "";
        try {
            isr = new InputStreamReader(new FileInputStream(path));
            char[] data = new char[50];
            int i = -1;
            while ((i = isr.read(data)) != -1) {
                content += new String(data, 0, i);
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    LOGGER.error("", e);
                } finally {
                    isr = null;
                }
            }
        }
        return content;
    }
}
