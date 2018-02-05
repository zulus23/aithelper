package ru.zhukov.ait.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import ru.zhukov.ait.domain.Enterprise;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Lazy
    @Bean
    @Scope("prototype")
    public DataSource dataSource(Enterprise enterprise){
      return null;
    }
}
