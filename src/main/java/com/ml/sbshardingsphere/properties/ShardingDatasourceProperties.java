package com.ml.sbshardingsphere.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @NAME: SharingDatasourceConfig
 * @DATE: 2019/6/13
 * @Author Mr.MaL
 * @Description TODO
 **/
@Configuration
@ConfigurationProperties(prefix="sharding")
public class ShardingDatasourceProperties {

    private List<DatasouceProperties> datasource;

    public List<DatasouceProperties> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<DatasouceProperties> datasource) {
        this.datasource = datasource;
    }
}
