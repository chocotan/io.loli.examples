package io.loli.example.ibatis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.github.miemiedev.mybatis.paginator.springmvc.PageListAttrHandlerInterceptor;

// 相当于<mvc:annotation-driven/>
@Configuration
// 包含<bean
// class="org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping"/>
@EnableWebMvc
@ComponentScan(basePackages = { "io.loli.example.ibatis.controller" })
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PageListAttrHandlerInterceptor())
                .excludePathPatterns("/");
    }

}
