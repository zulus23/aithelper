package ru.zhukov.ait.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.zhukov.ait.dao.ApplicationService;
import ru.zhukov.ait.dao.ApplicationServiceImpl;

@Configuration


public class ApplicationConfig {

    @Bean
    public ApplicationService applicationService(){
        return  new ApplicationServiceImpl();
    }

}
