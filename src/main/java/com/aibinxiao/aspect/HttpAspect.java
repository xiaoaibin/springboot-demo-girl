package com.aibinxiao.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aibin on 2017/6/8.
 */
@Aspect // 切面注解
@Component // 把这个文件引入到Spring容器中
public class HttpAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    // 在httpq请求之前记录下来，使用@Before注解
    // 在请求获取所有女生信息的时候拦截
    // @Before("execution(public * com.aibinxiao.controller.GirlController.girlList())") 指定某个类的某个方法
    // @Before("execution(public * com.aibinxiao.controller.GirlController.*())") 指定某个类的所有方法
    /*@Before("execution(public * com.aibinxiao.controller.GirlController.girlList())")
    public void log(){
        System.out.println("1111111111");
    }*/
    // 在httpq请求之前记录下来，使用@Before注解
    // 在请求获取所有女生信息的时候拦截
    /*@After("execution(public * com.aibinxiao.controller.GirlController.girlList())")
    public void doAfter(){
        System.out.println("2222222222");
    }*/

    @Pointcut("execution(public * com.aibinxiao.controller.GirlController.*())")
    public void log(){

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){// JoinPoint 参与点，即和这次请求相关的点
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();// 获取request对象
        // url
        logger.info("url={}",request.getRequestURL());
        // method
        logger.info("method={}",request.getMethod());
        // ip
        logger.info("ip={}",request.getRemoteAddr());
        // 类方法 先获取类名 然后是方法名
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        // 参数
        logger.info("args={}",joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter(){
        logger.info("22222222222");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterRuturning(Object object){ // 获取请求后返回的参数
        logger.info("response={}", object);
    }
}
