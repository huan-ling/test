package com.blizzard.game.pojo;

import java.io.Serializable;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-03-06 15:03
 */
public class AttackDto implements Serializable {

    private static final long serialVersionUID = 6188399038110577647L;
    /**
     * 发起攻击的位置 -1表示友方英雄
     */
    private Integer sceneSite;
    /**
     * 攻击位置 site = -1表示敌方英雄
     */
    private Integer attackSite;

    public Integer getSceneSite() {
        return sceneSite;
    }

    public void setSceneSite(Integer sceneSite) {
        this.sceneSite = sceneSite;
    }

    public Integer getAttackSite() {
        return attackSite;
    }

    public void setAttackSite(Integer attackSite) {
        this.attackSite = attackSite;
    }

    @Override
    public String toString() {
        return "AttackDto{" +
                "sceneSite=" + sceneSite +
                ", attackSite=" + attackSite +
                '}';
    }
}
