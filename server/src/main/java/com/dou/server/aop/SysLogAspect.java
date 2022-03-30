package com.dou.server.aop;

import com.dou.server.model.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author yangjd
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    /** 定义切点 @Pointcut **/
    @Pointcut("execution(public * com.dou.server.controller..*.*(..))")
    public void logPoinCut() {}


    /** 切面配置通知 **/
    @Before("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        // 日志实体类
        SystemLog sysLog = new SystemLog();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        // 获取请求的类名和方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        // 获取映射地址
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        assert ra != null;
        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        sysLog.setMapping(request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString());

        // 获取请求的参数名和参数值
        String [] filterType = {"post","put"};
        if (Arrays.asList(filterType).contains(request.getMethod().toLowerCase(Locale.ROOT))) {
            String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
            Object[] paramValues = joinPoint.getArgs();
            List<Object> arguments  = new ArrayList<>();
            for (int i = 0; i < paramValues.length; i++) {
                // 过滤一些无效参数
                if (paramValues[i] instanceof ServletRequest || paramValues[i] instanceof ServletResponse
                        || paramValues[i] instanceof MultipartFile) {
                    continue;
                }
                arguments.add(paramNames[i]+"="+paramValues[i]);
            }
            String params;
            try {
                params = arguments.toString();
            } catch (Exception e) {
                params = Arrays.toString(arguments.toArray());
            }
            sysLog.setParams(params);
        } else {
            Enumeration<String> em = request.getParameterNames();
            List<String> params = new ArrayList<>();
            while (em.hasMoreElements()) {
                String name = em.nextElement();
                String value = request.getParameter(name);
                params.add(name + "=" +value);
            }
            sysLog.setParams(params.toString());
        }
        log.info("mapping ——> {}",sysLog.getMapping());
        log.info("method  ——> {}()",sysLog.getMethod());
        log.info("params  ——> {}",sysLog.getParams());
    }
}