package com.cloudable.blog.config.dbconfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源配置
 *
 * @author CloudSen
 */
@Slf4j
@Configuration
public class DataSourceConfig {

    static {
        log.info("======> [ INFO ] Loading DataSource Configuration...");
    }

    @Bean(name = "dynamicDataSource")
    public DataSourceRouting getDynamicDataSourceConfig() {
        DataSourceRouting dataSourceRouting = new DataSourceRouting();
        Map<Object, Object> targetDataSourceMap = new HashMap<>(16);
        targetDataSourceMap.put(DataSourceRouting.DbKey.DEV, getDevDataSource());
        dataSourceRouting.setDefaultTargetDataSource(getDevDataSource());
        dataSourceRouting.setTargetDataSources(targetDataSourceMap);
        return dataSourceRouting;
    }

    @Bean(name = "devDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.one")
    public DruidDataSource getDevDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}

