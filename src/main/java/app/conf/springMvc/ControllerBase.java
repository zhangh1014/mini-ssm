package app.conf.springMvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import app.common.ConstValue;
import app.common.Message;
import app.common.Message.MessageType;

public class ControllerBase {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpSession session;

	/**
	 * 设置登录对象到Session
	 * 
	 * @param obj
	 *            登录对象
	 */
	public <T> void setLoginObject(T obj) {
		session.setAttribute(ConstValue.SESSION_LOGIN_OBJECT_KEY, obj);
	}

	/**
	 * 从Session中获取登录对象
	 * 
	 * @return 登录对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T getLoginObject() {
		return (T) session.getAttribute(ConstValue.SESSION_LOGIN_OBJECT_KEY);
	}

	/**
	 * 从Session中删除登录对象
	 */
	public void removeLoginObject() {
		session.removeAttribute(ConstValue.SESSION_LOGIN_OBJECT_KEY);
	}

	/**
	 * 获取绝对路径
	 * 
	 * @param path
	 *            相对路径
	 * @return 绝对路径
	 */
	public String getRealPath(String path) {
		return request.getServletContext().getRealPath(path);
	}

	/**
	 * 显示消息 基于当前请求的消息显示
	 * 
	 * @param msgKey
	 *            消息编号或消息内容
	 */
	public void showMessage(String msg, MessageType msgType) {

		Message message = new Message();
		message.setMsg(msg);
		message.setMsgType(msgType);

		request.setAttribute(ConstValue.REQUEST_MESSAGE_KEY, message);
	}

	/**
	 * 显示消息 基于跨请求的消息显示
	 * 
	 * @param msgKey
	 *            消息编号或消息内容
	 * @param targetController
	 *            显示消息的目标控制器
	 */
	public void showMessage(String msg, MessageType msgType, Class<?> targetController) {

		// 消息处理
		// 将消息加入Session
		@SuppressWarnings("unchecked")
		Map<String, Message> messageMap = (Map<String, Message>) session
				.getAttribute(ConstValue.SESSION_MESSAGE_MAP_KEY);
		if (messageMap == null) {
			messageMap = new HashMap<String, Message>();
			session.setAttribute(ConstValue.SESSION_MESSAGE_MAP_KEY, messageMap);
		}

		Message message = new Message();
		message.setMsg(msg);
		message.setMsgType(msgType);

		messageMap.put(targetController.getName(), message);
	}
	
}
