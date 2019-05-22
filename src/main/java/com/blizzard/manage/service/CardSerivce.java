package com.blizzard.manage.service;

import com.blizzard.common.dto.ResponseDto;
import com.blizzard.common.entity.Card;
import com.blizzard.common.enums.CareerEnum;

import java.util.List;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-16 13:52
 */
public interface CardSerivce {

    void add(Card card);

    void batchAdd(String url);

    List<Card> getAll();

    List<Card> getCareerAll(CareerEnum careerEnum);
}
