package com.ayush.config_two_database.product;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.ayush.config_two_database.product",
    transactionManagerRef = "productTransactionManager",
    entityManagerFactoryRef="productEntityManeger"
    
)
public class ProductDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.dev-data")
    public DataSourceProperties productDataSourceProperties(){
        return new DataSourceProperties() ;
    }


    @Bean
    @Primary
    public DataSource productDataSource(@Qualifier("productDataSourceProperties")DataSourceProperties dataSourceProperties){
        return dataSourceProperties.initializeDataSourceBuilder().build() ;
    }

   
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean productEntityManeger(EntityManagerFactoryBuilder builder,@Qualifier("productDataSource") DataSource dataSource){
        Map<String,Object> mp = new HashMap<>() ;
        mp.put("hibernate.hbm2ddl.auto", "update");
        
        return builder.dataSource(dataSource).packages("com.ayush.config_two_database.product").persistenceUnit("product").properties(mp).build() ;
    } 

    @Bean
    @Primary
    public PlatformTransactionManager productTransactionManager(@Qualifier("productEntityManeger") EntityManagerFactory entityManager ){
        return new JpaTransactionManager(entityManager) ;
    }
    
}
