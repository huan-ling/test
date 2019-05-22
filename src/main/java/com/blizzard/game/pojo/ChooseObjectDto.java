package com.blizzard.game.pojo;

import com.blizzard.common.enums.ChooseObjectEnum;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 选择对象
 * @Author: Huan
 * @CreateTime: 2019-02-27 20:02
 */
public class ChooseObjectDto implements Serializable {

    private static final long serialVersionUID = -5041133028921285736L;

    private ChooseObjectEnum chooseObjectEnum;

    /**
     * 以下四个属性配合枚举类型为OTHER时使用
     */
    private List<Integer> indexList;

    private List<Integer> enemyList;

    private boolean heroIndex;

    private boolean enemyHeroIndex;

    public ChooseObjectEnum getChooseObjectEnum() {
        return chooseObjectEnum;
    }

    public void setChooseObjectEnum(ChooseObjectEnum chooseObjectEnum) {
        this.chooseObjectEnum = chooseObjectEnum;
    }

    public List<Integer> getIndexList() {
        return indexList;
    }

    public void setIndexList(List<Integer> indexList) {
        this.indexList = indexList;
    }

    public List<Integer> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(List<Integer> enemyList) {
        this.enemyList = enemyList;
    }

    public boolean isHeroIndex() {
        return heroIndex;
    }

    public void setHeroIndex(boolean heroIndex) {
        this.heroIndex = heroIndex;
    }

    public boolean isEnemyHeroIndex() {
        return enemyHeroIndex;
    }

    public void setEnemyHeroIndex(boolean enemyHeroIndex) {
        this.enemyHeroIndex = enemyHeroIndex;
    }

    @Override
    public String toString() {
        return "ChooseObjectDto{" +
                "chooseObjectEnum=" + chooseObjectEnum +
                ", indexList=" + indexList +
                ", enemyList=" + enemyList +
                ", heroIndex=" + heroIndex +
                ", enemyHeroIndex=" + enemyHeroIndex +
                '}';
    }
}
