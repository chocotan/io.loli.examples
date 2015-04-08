package io.loli.example.ibatis.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerInterceptor {
    @Before("execution(* io.loli.example.ibatis..*.insert(..))")
    public void logger(JoinPoint joinPoint) throws Exception {
        System.out.println(joinPoint.getArgs()[0].toString() + " added");
    }
}
