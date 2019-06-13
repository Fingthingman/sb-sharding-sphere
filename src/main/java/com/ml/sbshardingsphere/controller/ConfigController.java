package com.ml.sbshardingsphere.controller;

import com.ml.sbshardingsphere.entity.Config;
import com.ml.sbshardingsphere.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @NAME: ConfigController
 * @DATE: 2019/6/12
 * @Author Mr.MaL
 * @Description TODO
 **/
@RestController
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping("/save")
    public Boolean insert(Config config) {

        return configService.save(config);
    }
}
