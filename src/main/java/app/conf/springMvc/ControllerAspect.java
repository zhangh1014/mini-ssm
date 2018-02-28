package app.conf.springMvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.lechisoft.minifw.log.MiniLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.conf.common.ConstValue;
import app.conf.common.Message;

@Component
@Aspect
public class ControllerAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Pointcut("bean(*Controller) && !bean(messageController)")
    public void controller() {
    }

    @AfterThrowing(value = "controller()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        // 写日志：发生异常
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        MiniLog.error("controller -> " + className + " -> " + methodName + ": error.");
    }

    @Before(value = "controller()")
    public void before(JoinPoint joinPoint) {
        // 写日志：调用控制器开始
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        MiniLog.debug("controller -> " + className + " -> " + methodName + ": begin.");
        
        // begin:消息处理
        // 如果Session中存在消息，取出并放入Request
        @SuppressWarnings("unchecked")
        Map<String, Message> messageMap = (Map<String, Message>) session.getAttribute(ConstValue.SESSION_MESSAGE_MAP_KEY);
        if (messageMap != null) {
        	Message message = messageMap.get(className);
            request.setAttribute(ConstValue.REQUEST_MESSAGE_KEY, message);
            messageMap.remove(className);
        }
        // end:消息处理
    }

    @After(value = "controller()")
    public void after(JoinPoint joinPoint) {
        // 写日志：调用控制器结束
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        MiniLog.debug("controller -> " + className + " -> " + methodName + ": end.");
    }

}
