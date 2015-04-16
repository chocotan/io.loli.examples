package io.loli.example.spring.jpa.config;

import java.util.Arrays;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Jsr330ScopeMetadataResolver;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAspectJAutoProxy
// @EnableScheduling
@PropertySource("classpath:database.properties")
@Configuration
@EnableTransactionManagement
@EnableCaching
@ComponentScan(basePackages = { "io.loli.example.spring.jpa.dao",
        "io.loli.example.spring.jpa.service" }, scopeResolver = Jsr330ScopeMetadataResolver.class)
public class ContextConfig {

    @Value(value = "${db.driver}")
    private String driverClass;
    @Value(value = "${db.username}")
    private String username;
    @Value(value = "${db.password}")
    private String password;
    @Value(value = "${db.url}")
    private String url;

    @Value(value = "${db.showSql}")
    private Boolean showSql = true;

    @Value(value = "${db.generateDdl}")
    private boolean generateDdl = true;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName(driverClass);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);

        ds.setInitialSize(1);
        ds.setMinIdle(3);
        ds.setMaxIdle(5);
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean emfb = new org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean();
        emfb.setPackagesToScan("io.loli.lightbbs.entity");
        emfb.setJpaVendorAdapter(jpaVendorAdapter());
        emfb.setDataSource(dataSource());
        emfb.setPersistenceUnitName("lightbbs");
        return emfb;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {

        HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
        vendor.setShowSql(showSql);
        vendor.setGenerateDdl(generateDdl);
        vendor.setDatabase(Database.MYSQL);
        return vendor;
    }

    @Bean
    public JpaDialect jpaDialect() {
        return new HibernateJpaDialect();
    }

    @Bean
    public CacheManager cacheManager() {
        // configure and return an implementation of Spring's CacheManager SPI
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays
                .asList(new ConcurrentMapCache("lightbbs")));
        return cacheManager;
    }

    @Bean
    public JpaTransactionManager annotationDrivenTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean()
                .getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
