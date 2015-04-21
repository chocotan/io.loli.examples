package io.loli.example.spring.multids.db;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MultiDSInterceptor {

//    @Before("execution(* cn.zto.pda.mapper..*.*(..))")
    public void dynamicSetDataSoruce(JoinPoint joinPoint) throws Exception {
        DataSource dataSoruce = joinPoint.getTarget().getClass()
                .getAnnotation(DataSource.class);
        dataSoruce = dataSoruce == null ? ((MethodSignature) joinPoint
                .getSignature()).getMethod().getAnnotation(DataSource.class)
                : dataSoruce;
        if (dataSoruce != null) {
            ContextHolder.setCustomerType(dataSoruce.value());
        } else {
            ContextHolder.clearCustomerType();
        }
    }
    
}
