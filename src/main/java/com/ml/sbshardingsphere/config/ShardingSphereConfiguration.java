package com.ml.sbshardingsphere.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.ml.sbshardingsphere.properties.DatasouceProperties;
import com.ml.sbshardingsphere.properties.DruidProperties;
import com.ml.sbshardingsphere.properties.ShardingDatasourceProperties;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @NAME: ShardingSphereConfiguration
 * @DATE: 2019/6/13
 * @Author Mr.MaL
 * @Description TODO
 **/

@Configuration

public class ShardingSphereConfiguration {

    @Autowired
    private ShardingDatasourceProperties shardingDatasourceProperties;
    @Autowired
    private DruidProperties druidProperties;

    private DruidDataSource druidDataSource() throws SQLException {
        DruidDataSource ds = new DruidDataSource();
        ds.setFilters(druidProperties.getFilters());
        ds.setMaxActive(druidProperties.getMaxActive());
        ds.setInitialSize(druidProperties.getInitialSize());
        ds.setMaxWait(druidProperties.getMaxWait());
        ds.setMinIdle(druidProperties.getMinIdle());
        ds.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
        ds.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
        ds.setValidationQuery(druidProperties.getValidationQuery());
        ds.setTestWhileIdle(druidProperties.isTestWhileIdle());
        ds.setTestOnBorrow(druidProperties.isTestOnBorrow());
        ds.setTestOnReturn(druidProperties.isTestOnReturn());
        ds.setPoolPreparedStatements(druidProperties.isPoolPreparedStatements());
        ds.setMaxPoolPreparedStatementPerConnectionSize(druidProperties.getMaxPoolPreparedStatementPerConnectionSize());
        ds.setRemoveAbandoned(druidProperties.isRemoveAbandoned());
        ds.setRemoveAbandonedTimeout(druidProperties.getRemoveAbandonedTimeout());
        ds.setLogAbandoned(druidProperties.isLogAbandoned());
        ds.setConnectionInitSqls(druidProperties.getConnectionInitSqls());
        return ds;
    }

    private Map<String, DataSource> shardingDataSourceMap() throws SQLException {
        List<DatasouceProperties> datasouceProperties = shardingDatasourceProperties.getDatasource();
        Map<String, DataSource> dataSourceMap = null;
        if (Objects.nonNull(datasouceProperties)) {
            dataSourceMap = new HashMap<>(datasouceProperties.size());
            for (DatasouceProperties properties : datasouceProperties) {
                DruidDataSource ds = druidDataSource();
                ds.setUsername(properties.getUsername());
                ds.setUrl(properties.getUrl());
                ds.setPassword(properties.getPassword());
                dataSourceMap.put(properties.getName(), ds);
            }
        }
        return dataSourceMap;
    }

    TableRuleConfiguration getUserTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("user");
        result.setActualDataNodes("ds${0..1}.user_${0..1}");
        result.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "ds${id % 2}"));
        result.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("age", "user_$->{age %2}"));
        return result;
    }

    @Bean("shardingDataSource")
    public DataSource shardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
//        shardingRuleConfig.getBroadcastTables().add("config");
        shardingRuleConfig.setDefaultDataSourceName("ds0");
        return ShardingDataSourceFactory.createDataSource(shardingDataSourceMap(), shardingRuleConfig, new ConcurrentHashMap<>(), new Properties());
    }


}
