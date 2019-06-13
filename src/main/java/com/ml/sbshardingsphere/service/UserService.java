package com.ml.sbshardingsphere.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ml.sbshardingsphere.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    boolean save(User user);

    List<User> getUSerList();

}
