package com.cloudable.blog.config.transaction;

import com.cloudable.blog.config.dbconfig.DataSourceRouting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;

/**
 * 集中管理事务配置
 *
 * @author CloudSen
 */
@Configuration
public class TransactionConfig {

    private final DataSourceRouting dynamicDataSource;

    public TransactionConfig(DataSourceRouting dynamicDataSource) {
        this.dynamicDataSource = dynamicDataSource;
    }

    @Bean(name = "jpaTransactionManager")
    public PlatformTransactionManager getJpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager getMybatisTransactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
