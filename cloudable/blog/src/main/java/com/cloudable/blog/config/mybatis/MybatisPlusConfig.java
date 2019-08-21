package com.cloudable.blog.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.cloudable.blog.config.dbconfig.DataSourceConfig;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * mybatis plus配置
 *
 * @author CloudSen
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.collapseunion.commonapi.cloudable.blog.mapper")
public class MybatisPlusConfig {

    private final DataSourceConfig dataSourceConfig;

    public MybatisPlusConfig(DataSourceConfig dataSource) {
        this.dataSourceConfig = dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:cloudable/blog/*.xml");
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        // 设置数据源
        factoryBean.setDataSource(dataSourceConfig.getDynamicDataSourceConfig());
        // 设置sql xml文件路径
        factoryBean.setMapperLocations(resources);
        factoryBean.setTypeAliasesPackage("com.collapseunion.commonapi.cloudable.blog");
        factoryBean.setPlugins(new Interceptor[]{paginationInterceptor()});
        return factoryBean.getObject();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 性能分析插件
     */
    @Bean
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //格式化sql语句
        Properties properties = new Properties();
        properties.setProperty("format", "true");
        properties.setProperty("maxTime", "10000");
        performanceInterceptor.setProperties(properties);
        return performanceInterceptor;
    }
}
