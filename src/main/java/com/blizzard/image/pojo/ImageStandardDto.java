package com.blizzard.image.pojo;

import java.io.Serializable;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-04-15 18:26
 */
public class ImageStandardDto implements Serializable {

    private static final long serialVersionUID = -6634979420084867276L;
    private String name;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ImageStandardDto{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
