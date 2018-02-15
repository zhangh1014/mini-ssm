package app.conf.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.lechisoft.minifw.log.MiniLog;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceAspect {

    @Pointcut("execution(* app.service..*.*(..))")
    public void service() {
    }

    @AfterThrowing(value = "service()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        // 写日志：发生异常
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        MiniLog.error("service -> " + className + " -> " + methodName + ": error.");
    }

    @Before(value = "service()")
    public void before(JoinPoint joinPoint) {
        // 写日志：调用控制器开始
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        MiniLog.debug("service -> " + className + " -> " + methodName + ": begin.");
    }

    @After(value = "service()")
    public void after(JoinPoint joinPoint) {
        // 写日志：调用控制器结束
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        MiniLog.debug("service -> " + className + " -> " + methodName + ": end.");
    }

}
