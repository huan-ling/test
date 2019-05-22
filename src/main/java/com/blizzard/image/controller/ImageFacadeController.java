package com.blizzard.image.controller;

import com.blizzard.common.dto.ResponseDto;
import com.blizzard.image.pojo.ImageStandardDto;
import com.blizzard.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-04-15 18:24
 */
@RestController
@RequestMapping("imageFacade")
public class ImageFacadeController {

    @Autowired
    private ImageService imageService;

    @PostMapping("uploadStandard")
    public ResponseDto<?> uploadStandard(ImageStandardDto imageStandardDto){
        imageService.uploadStandard(imageStandardDto);
        return new ResponseDto<>();
    }
}
