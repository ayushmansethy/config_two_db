package com.ayush.config_two_database.users;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.ayush.config_two_database.users",
    transactionManagerRef = "usersTransactionManager",
    entityManagerFactoryRef = "usersEntityManeger"
)
public class UsersDatasourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    public DataSourceProperties usersDataSourceProperties(){
        return new DataSourceProperties() ;
    }


    @Bean
    public DataSource usersDataSource(@Qualifier("usersDataSourceProperties")DataSourceProperties dataSourceProperties){
        return dataSourceProperties.initializeDataSourceBuilder().build() ;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean usersEntityManeger(EntityManagerFactoryBuilder builder,@Qualifier("usersDataSource") DataSource dataSource){
        return builder.dataSource(dataSource).packages("com.ayush.config_two_database.users").persistenceUnit("users").build() ;
    } 

    @Bean
    public PlatformTransactionManager usersTransactionManager(@Qualifier("usersEntityManeger") EntityManagerFactory entityManager ){
        return new JpaTransactionManager(entityManager) ;
    }

    
}