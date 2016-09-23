package org.reflection.config;

import org.reflection.service.DataConverterFactory;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.reflection.repositories")
public class PersistenceConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataConverterFactory dataConverterFactory() {
        EntityManager em = entityManagerFactory().getObject().createEntityManager();
        return new DataConverterFactory(em);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties hibernate = new Properties();
//        org.hibernate.ejb.HibernatePersistence kk;
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
        hibernate.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return hibernate;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean();
        //entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceUnitName("mchti_PU");
        //entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty("entitymanager.packages.to.scan"));
        //entityManagerFactoryBean.setJpaProperties(hibernateProperties());
        //HibernateJpaVendorAdapter hhh = new org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter();
        //hhh.setDatabasePlatform(environment.getRequiredProperty("hibernate.dialect"));
        //hhh.setShowSql(true);
        //entityManagerFactoryBean.setJpaVendorAdapter(hhh);
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        org.springframework.orm.jpa.JpaTransactionManager transactionManager = new org.springframework.orm.jpa.JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
