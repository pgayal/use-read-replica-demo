package com.example.usereadreplicademo.config;

import com.example.usereadreplicademo.annotation.ReadOnlyRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * This is read only data source configuration which connects to read only instance(read-replica) of the DB
 *
 * @author pgayal
 * created on 02/20/2022
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.usereadreplicademo",
        includeFilters = @ComponentScan.Filter(ReadOnlyRepository.class),
        entityManagerFactoryRef = "readOnlyEntityManagerFactory"
)
public class ReadOnlyDataSourceConfiguration {

    @Bean(name = "readOnlyDataSource")
    @ConfigurationProperties(prefix = "spring.readonly.datasource")
    public DataSource readOnlyDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "readOnlyEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean readOnlyEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("readOnlyDataSource") DataSource readOnlyDataSource) {
        return builder
                .dataSource(readOnlyDataSource)
                .packages("com.example.usereadreplicademo.model")
                .properties(jpaProperties())
                .persistenceUnit("readOnlyDataSource")
                .build();
    }

    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        return props;
    }
}
