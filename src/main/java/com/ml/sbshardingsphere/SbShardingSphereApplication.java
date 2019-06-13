package com.ml.sbshardingsphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ml.sbshardingsphere.mapper")
@SpringBootApplication
public class SbShardingSphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbShardingSphereApplication.class, args);

    }

}
