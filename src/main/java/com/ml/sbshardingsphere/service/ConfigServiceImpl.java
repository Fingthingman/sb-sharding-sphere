package com.ml.sbshardingsphere.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ml.sbshardingsphere.entity.Config;
import com.ml.sbshardingsphere.mapper.ConfigMapper;
import org.springframework.stereotype.Service;

/**
 * @NAME: ConfigServiceImpl
 * @DATE: 2019/6/12
 * @Author Mr.MaL
 * @Description TODO
 **/
@Service
public class ConfigServiceImpl  extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

    @Override
    public boolean save(Config entity){
        return super.save(entity);
    }
}
