package app.conf.springMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lechisoft.minifw.log.MiniLog;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import app.common.ConstValue;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String MyExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		request.setAttribute(ConstValue.EXCEPTION_MESSAGE_KEY, ex.getMessage());
		StringBuffer stb = new StringBuffer();
		StackTraceElement[] stackTraceArray = ex.getStackTrace();
		for (StackTraceElement element : stackTraceArray) {
			stb.append(element.toString()).append("\r\n");
		}
		request.setAttribute(ConstValue.EXCEPTION_STACK_TRACE_KEY, stb.toString());

		MiniLog.error(ConstValue.EXCEPTION_MESSAGE_KEY + ": " + ex.getMessage());
		MiniLog.error(ConstValue.EXCEPTION_STACK_TRACE_KEY + ": " + stb.toString());

		return "error";
	}
}
