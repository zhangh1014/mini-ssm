package app.conf.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReturnModel<T> {
	public enum ReturnStatus {
		Success, Fail
	}

	// 返回消息
	private ReturnMessage message = null;
	private List<ReturnMessage> messageList = new ArrayList<ReturnMessage>();
	private Map<String, ReturnMessage> messageMap = new HashMap<String, ReturnMessage>();

	// 返回对象
	private T model = null;
	private List<T> modelList = new ArrayList<T>();
	private Map<String, T> modelMap = new HashMap<String, T>();

	private ReturnStatus status = ReturnStatus.Success;

	public ReturnMessage getMessage() {
		return message;
	}

	public void setMessage(ReturnMessage message) {
		this.message = message;
	}

	public List<ReturnMessage> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<ReturnMessage> messageList) {
		this.messageList = messageList;
	}

	public Map<String, ReturnMessage> getMessageMap() {
		return messageMap;
	}

	public void setMessageMap(Map<String, ReturnMessage> messageMap) {
		this.messageMap = messageMap;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public List<T> getModelList() {
		return modelList;
	}

	public void setModelList(List<T> modelList) {
		this.modelList = modelList;
	}

	public Map<String, T> getModelMap() {
		return modelMap;
	}

	public void setModelMap(Map<String, T> modelMap) {
		this.modelMap = modelMap;
	}

	public ReturnStatus getStatus() {
		return status;
	}

	public void setStatus(ReturnStatus status) {
		this.status = status;
	}
}