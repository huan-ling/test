package com.blizzard.manage.service.impl;

import com.blizzard.common.enums.CareerEnum;
import com.blizzard.common.util.JsonEnumUtil;
import com.blizzard.manage.service.CardCareerService;
import org.springframework.stereotype.Service;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-16 12:51
 */
@Service("cardCareeerService")
public class CardCareerServiceImpl implements CardCareerService {
    @Override
    public String get() {
        return JsonEnumUtil.enum2Json(CareerEnum.class);
    }
}
