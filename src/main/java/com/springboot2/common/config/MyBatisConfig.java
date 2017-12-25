package com.springboot2.common.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = "com.springboot2.business.sample.mapper")
public class MyBatisConfig {

    @Autowired
    DataSourceProperties dataSourceProperties;

    @Bean
    DataSource druidDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", this.dataSourceProperties.getDriverClassName());
        props.put("url", this.dataSourceProperties.getUrl());
        props.put("username", this.dataSourceProperties.getUsername());
        props.put("password", this.dataSourceProperties.getPassword());
        //        props.put("initialSize", "5");
        //        props.put("minIdle", "10");
        //        props.put("maxIdle", "15");
        //        props.put("maxActive", "1");
        props.put("testWhileIdle", "true");
        props.put("testOnBorrow", "SELECT 1 FROM DUAL");

        return DruidDataSourceFactory.createDataSource(props);

        // return new Log4jdbcProxyDataSource(this.dataSource);
    }

    @Bean
    @Primary
    DataSource hikariDataSource() throws Exception {

        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName(this.dataSourceProperties.getDriverClassName());
        ds.setJdbcUrl(this.dataSourceProperties.getUrl());
        ds.setUsername(this.dataSourceProperties.getUsername());
        ds.setPassword(this.dataSourceProperties.getPassword());
        //        ds.setMinimumIdle(5);
        //        ds.setMaximumPoolSize(5);
        ds.setConnectionTestQuery("SELECT 1 FROM DUAL");
        return ds;

        // return new Log4jdbcProxyDataSource(this.dataSource);
    }

    @Bean
    public SqlSessionFactory getSqlSessionFactory(DataSource ds) throws Exception {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // factoryBean.setDataSource(druidDataSource());
        factoryBean.setDataSource(hikariDataSource());
        factoryBean.setTypeAliasesPackage("com.springboot2.business.sample.dataset");
        factoryBean
                .setMapperLocations(new PathMatchingResourcePatternResolver()
                        .getResources("classpath:com/springboot2/business/sample/mapper/*.xml"));

        return factoryBean.getObject();
    }
}
