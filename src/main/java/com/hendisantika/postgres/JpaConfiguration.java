package com.hendisantika.postgres;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JpaConfiguration {

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
//                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url(getDataSourceUrl())
                .username("postgres")
                .password("changeme")
                .build();
    }

    private String getDataSourceUrl() {
        String url = "jdbc:postgresql://"
                + System.getenv("DB_HOST") + ":"
                + System.getenv("DB_PORT") + "/"
                + System.getenv("DB_NAME");
        return url;
    }
}
