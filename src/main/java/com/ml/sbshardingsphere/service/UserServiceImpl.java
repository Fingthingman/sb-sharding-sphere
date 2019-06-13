package com.ml.sbshardingsphere.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ml.sbshardingsphere.entity.User;
import com.ml.sbshardingsphere.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @NAME: UserServiceImpl
 * @DATE: 2019/6/12
 * @Author Mr.MaL
 * @Description TODO
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    public List<User> getUSerList() {
        return baseMapper.selectList(Wrappers.<User>lambdaQuery());
    }
}
