package com.cvicse.jy1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class ActivitiDataSourceConfig {

    @Value("${activiti.datasource.url}")
    private String url;

    @Value("${activiti.datasource.username}")
    private String username;

    @Value("${activiti.datasource.password}")
    private String password;

    @Value("${activiti.datasource.hikari.poolName}")
    private String poolName;

    @Value("${activiti.datasource.hikari.auto-commit}")
    private boolean autoCommit;

    @Bean(name = "activitiDataSource")
    public HikariDataSource activitiDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setPoolName(poolName);
        config.setAutoCommit(autoCommit);
        return new HikariDataSource(config);
    }
}
