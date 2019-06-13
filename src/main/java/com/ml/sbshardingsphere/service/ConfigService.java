package com.ml.sbshardingsphere.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ml.sbshardingsphere.entity.Config;

public interface ConfigService extends IService<Config> {

    boolean save(Config config);
}
