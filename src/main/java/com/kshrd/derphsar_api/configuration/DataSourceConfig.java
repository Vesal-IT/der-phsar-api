//package com.example.derphsar_api.configuration;
//
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
//    public DataSource myPostgrestDb(){
//        DataSourceBuilder dataSource = DataSourceBuilder.create();
//        dataSource.driverClassName("org.postgresql.Driver");
//        dataSource.url("jdbc:postgresql://localhost:5432/article");
//        dataSource.username("postgres");
//        dataSource.password("123");
//        return dataSource.build();
//    }
//}
