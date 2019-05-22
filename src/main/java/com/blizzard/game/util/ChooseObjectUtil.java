package com.blizzard.game.util;

import com.blizzard.common.enums.ChooseObjectEnum;
import com.blizzard.game.pojo.ChooseObjectDto;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-27 20:10
 */
public class ChooseObjectUtil {

    public static ChooseObjectDto get(ChooseObjectEnum chooseObjectEnum){
        ChooseObjectDto chooseObjectDto = new ChooseObjectDto();
        chooseObjectDto.setChooseObjectEnum(chooseObjectEnum);
        return chooseObjectDto;
    }

}
