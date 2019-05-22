package com.blizzard.image.service.impl;

import com.blizzard.image.pojo.Image;
import com.blizzard.image.pojo.ImageStandardDto;
import com.blizzard.image.service.ImageService;
import com.blizzard.image.util.ImageAnalysis;
import com.blizzard.image.util.ImageFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-04-15 18:40
 */
@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public void uploadStandard(ImageStandardDto imageStandardDto) {
        String src = ImageFileUtil.saveImage2File(imageStandardDto.getName(),imageStandardDto.getContent());
        Image image = new Image(imageStandardDto.getContent());
        ImageAnalysis.analysis(image);
    }




}
