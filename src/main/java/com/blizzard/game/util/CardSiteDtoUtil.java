package com.blizzard.game.util;

import com.blizzard.game.pojo.CardGameDto;
import com.blizzard.game.pojo.CardSiteDto;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-03-02 18:00
 */
public class CardSiteDtoUtil {

    public static CardSiteDto getCardSiteDto(Integer site, CardGameDto cardGameDto){
        CardSiteDto cardSiteDto = new CardSiteDto();
        cardSiteDto.setCardGameDto(cardGameDto);
        cardSiteDto.setSite(site);
        return cardSiteDto;
    }
}
