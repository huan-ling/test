package com.blizzard.manage.service.impl;

import com.blizzard.common.enums.CardRaceEnum;
import com.blizzard.common.util.JsonEnumUtil;
import com.blizzard.manage.service.CardRaceService;
import org.springframework.stereotype.Service;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-16 12:29
 */
@Service("cardRaceService")
public class CardRaceServiceImpl implements CardRaceService {

    @Override
    public String get() {
        return JsonEnumUtil.enum2Json(CardRaceEnum.class);
    }

}
