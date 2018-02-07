package ru.zhukov.ait.config;

import net.sourceforge.jtds.jdbcx.JtdsDataSource;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.zhukov.ait.domain.Enterprise;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"ru.zhukov.ait"})
@EnableJpaRepositories(basePackages = {"ru.zhukov.ait"})
@EnableTransactionManagement
public class DatabaseConfig {


    @Lazy
    @Bean
    public Enterprise enterprise(){
       return  Enterprise.GOTEK;
    }


    @Lazy
    @Bean
    public DataSource dataSource(Enterprise enterprise){
        JtdsDataSource dataSource = new JtdsDataSource();
        if(System.getProperty("user.name").equals("Zhukov")){
            dataSource.setServerName("localhost");
            //dataSource.setInstance("AIT");
            dataSource.setUser("sa");
            dataSource.setPassword("415631234");

        } else {
            dataSource.setServerName("srv-sqlbox");
            dataSource.setInstance("AIT");
            dataSource.setUser("report");
            dataSource.setPassword("report");
        }
        dataSource.setDatabaseName(enterprise.nameDb());
        return dataSource;
    }



    @Bean
    @Lazy
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory(enterprise()));
    }

    @Bean
    @Lazy
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Lazy
    @Bean
    public EntityManagerFactory entityManagerFactory(Enterprise enterprise){
        LocalContainerEntityManagerFactoryBean factoryBean =
                  new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("ru.zhukov.ait.domain");
        factoryBean.setDataSource(dataSource(enterprise));
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.afterPropertiesSet();

        return factoryBean.getNativeEntityManagerFactory();
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        //hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProp.put("hibernate.format_sql", true);
       // hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
        return hibernateProp;
    }


}
