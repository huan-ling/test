package com.blizzard.common.constant;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-24 17:14
 */
public interface Constant {

    /**
     * 幸运币id
     */
    int LUCKY_CARD_ID = 140;

    /**
     * 最大允许的法力值
     */
    int MAX_MANA = 10;

    /**
     * 处理卡牌作用的类所在的包位置
     */
    String CARD_PACAKAGE = "com.blizzard.game.card.impl.Card";

    String CARD_BASE_PACAKAGE = "com.blizzard.game.card.CardInt";

    /**
     * 场面的卡牌最大数量
     */
    int SCENE_CARD_MAX = 7;

    /**
     * 手牌的最大数量
     */
    int HAND_CARD_MAX = 10;
}
