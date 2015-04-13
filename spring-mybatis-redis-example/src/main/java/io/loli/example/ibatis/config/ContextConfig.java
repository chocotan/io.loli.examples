package io.loli.example.ibatis.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@EnableAspectJAutoProxy
// @EnableScheduling
@PropertySource("classpath:database.properties")
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "io.loli.example.ibatis" })
@MapperScan("io.loli.example.ibatis.mapper")
public class ContextConfig {

    @Value(value = "${db.driver}")
    private String driverClass;
    @Value(value = "${db.username}")
    private String username;
    @Value(value = "${db.password}")
    private String password;
    @Value(value = "${db.url}")
    private String url;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driverClass);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);

        ds.setInitialSize(1);
        ds.setMinIdle(2);
        ds.setMaxActive(60);
        return ds;
    }

    @Bean
    @Autowired
    public SqlSessionFactoryBean sqlSessionFactoryBean(
            ApplicationContext applicationContext) throws IOException {
        SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
        ssf.setDataSource(dataSource());
        ssf.setConfigLocation(new ClassPathResource("ibatis.xml"));
        // ssf.setMapperLocations(applicationContext
        //        .getResources("classpath:ibatis/*.xml"));
        ssf.setTypeAliasesPackage("io.loli.example.ibatis.mapper");
        return ssf;

    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    // replaced with @MapperScan
    // @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer m = new MapperScannerConfigurer();
        m.setBasePackage("io.loli.example.ibatis.mapper");
        return m;
    }

    // replaced with @ComponentScan
    // this bean will make @PropertySource fail
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

}
