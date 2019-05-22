package com.blizzard.image.pojo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-04-16 17:34
 */
public class Site implements Serializable {

    private static final long serialVersionUID = 7151859472982287252L;
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Site{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return x == site.x &&
                y == site.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }
}
