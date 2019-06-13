package com.ml.sbshardingsphere.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * @NAME: Config
 * @DATE: 2019/6/12
 * @Author Mr.MaL
 * @Description TODO
 **/
@TableName("config")
public class Config extends Model<Config> {
    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
