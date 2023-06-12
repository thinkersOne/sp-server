package com.pj.current.global;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.druid.util.StringUtils;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.pj.project.apilog.SpApilogUtil;
import com.pj.utils.sg.AjaxJson;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * API全局日志, Controller 层切面 
 *
 * @author kong
 */
@Aspect
@Component
@Log4j2
public class ApilogAspect {
    
	/**
	 * 定义AOP签名 --> 项目代码(所有class名成带有Controller字符的)
	 */
	@Pointcut("execution(* com.pj..*Controller*.*(..))")
    public void webLogProject(){}

    //对切点方法进行前置增强，就是在调用切点方法前进行做一些必要的操作，这就成为增强
    @Before("webLogProject()")
    public void getRes(JoinPoint joinPoint) throws IntrospectionException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        log.info("开始拦截。。。");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("AopDTO: {}", arg);
            if(arg == null){
                continue;
            }
            //
            Class<?> aClass = arg.getClass();
            Field[] fields = aClass.getFields();
            for (Field field: fields) {
                //设置对象的访问权限，保证对private的属性的访问
                field.setAccessible(true);
                Object value = field.get(arg);
                //设置默认值
                if("createBy".equalsIgnoreCase(field.getName()) && ObjectUtil.isEmpty(value)){
                    aClass.getDeclaredField(field.getName()).set(arg,"admin");
                }
                if("updateBy".equalsIgnoreCase(field.getName()) && ObjectUtil.isEmpty(value)){
                    aClass.getDeclaredField(field.getName()).set(arg,"admin");
                }
                if("createTime".equalsIgnoreCase(field.getName()) && ObjectUtil.isEmpty(value)){
                    aClass.getDeclaredField(field.getName()).set(arg, LocalDateTime.now().toString());
                }
                if("updateTime".equalsIgnoreCase(field.getName()) && ObjectUtil.isEmpty(value)){
                    aClass.getDeclaredField(field.getName()).set(arg,LocalDateTime.now().toString());
                }
            }
        }
    }

    /**
     * 环绕日志 
     * @param pjp
     * @return 
     * @throws Throwable
     */
    @Around("webLogProject()")
    public Object surround(ProceedingJoinPoint pjp) throws Throwable {
        try {
        	// 1、执行 
            Object obj =  pjp.proceed();
            // 2、解析返回结果 
            // 如果是 AjaxJson 
            if(obj instanceof AjaxJson){	
            	SpApilogUtil.endRequest((AjaxJson)obj);
            } 
            // 如果是 String  
            else if (obj instanceof String) {	
            	SpApilogUtil.endRequest(AjaxJson.get(901, String.valueOf(obj)));
            } 
            // 如果都不是 
            else {	 
            	SpApilogUtil.endRequest(AjaxJson.get(902, String.valueOf(obj)));
            }
            return obj;
        } catch (Throwable e) {
        	throw e;
        }
    }
    
}