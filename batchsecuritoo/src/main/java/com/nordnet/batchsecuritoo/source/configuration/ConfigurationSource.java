package com.nordnet.batchsecuritoo.source.configuration;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "sourceEntityManagerFactory",
        transactionManagerRef = "sourceTransactionManager",
        basePackages = {"com.nordnet.batchsecuritoo.source.repository"})
public class ConfigurationSource {

    @Bean(name = "sourceDataSource")
    @ConfigurationProperties(prefix = "source.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sourceEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sourceEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("sourceDataSource") DataSource sourceDataSource) {
        return builder
                .dataSource(sourceDataSource)
                .packages("com.nordnet.batchsecuritoo.source.entity")
                .persistenceUnit("sourcedb")
                .build();
    }

    @Bean(name = "sourceTransactionManager")
    public PlatformTransactionManager sourceTransactionManager(
            @Qualifier("sourceEntityManagerFactory") EntityManagerFactory sourceEntityManagerFactory) {
        return new JpaTransactionManager(sourceEntityManagerFactory);
    }

}
