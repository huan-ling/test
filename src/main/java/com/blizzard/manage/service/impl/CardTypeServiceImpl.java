package com.blizzard.manage.service.impl;

import com.blizzard.common.enums.CardTypeEnum;
import com.blizzard.common.util.JsonEnumUtil;
import com.blizzard.manage.service.CardTypeService;
import org.springframework.stereotype.Service;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-15 15:44
 */
@Service("cardTypeServiceImpl")
public class CardTypeServiceImpl implements CardTypeService {

    @Override
    public String get() {
        return JsonEnumUtil.enum2Json(CardTypeEnum.class);
    }

}

