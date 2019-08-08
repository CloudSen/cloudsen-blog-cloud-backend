package com.cloudable.blog.config.jpa;

import com.cloudable.blog.config.dbconfig.DataSourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Spring Data JPA 配置（停用）
 *
 * @author CloudSen
 */
@Slf4j
@EnableJpaRepositories(
        basePackages = "com.collapseunion.commonapi.cloudable.blog.repository",
        transactionManagerRef = "jpaTransactionManager"
)
@EnableTransactionManagement
@Deprecated
@Configuration
public class JpaConfig {

    static {
        log.info("======> [ INFO ] Loading JPA Configuration...");
    }

    private final DataSourceConfig dataSourceConfig;

    public JpaConfig(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(false);
        factoryBean.setDataSource(dataSourceConfig.getDynamicDataSourceConfig());
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("com.collapseunion.commonapi.cloudable.blog.entity");
        return factoryBean;
    }
}
