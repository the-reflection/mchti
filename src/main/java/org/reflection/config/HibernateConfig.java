package org.reflection.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"org.reflection.config"})
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
public class HibernateConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        //org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));

//dataSourcet.setDriverClassName("org.h2.Driver");
//dataSourcet.setUrl("jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE");
//dataSourcet.setUsername("sa");
//dataSourcet.setPassword("");        
//dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
//dataSource.setUrl("jdbc:derby://localhost:1527/app");
//dataSource.setUsername("app");
//dataSource.setPassword("app");
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties hibernate = new Properties();

        hibernate.put("cache.use_second_level_cache", "true");
        hibernate.put("cache.use_query_cache", "false");
        hibernate.put("cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        hibernate.put("singleSession", "true");
        hibernate.put("flush.mode", "manual");
        //useful for debugging
        hibernate.put("hibernate.generate_statistics", "true");
        hibernate.put("hibernate.connection.charSet", "UTF-8");

        hibernate.put("hibernate.ejb.naming_strategy", environment.getRequiredProperty("hibernate.ejb.naming_strategy"));
        hibernate.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        hibernate.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        hibernate.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        hibernate.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));//create, update, create-drop, validate

        return hibernate;
    }

    @Autowired
    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder localSessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource());
        localSessionFactoryBuilder.scanPackages(new String[]{"org.reflection.model"});
        localSessionFactoryBuilder.addProperties(hibernateProperties());
        return localSessionFactoryBuilder.buildSessionFactory();
    }

     @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
}
