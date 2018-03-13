package app.conf.common;

import java.util.ArrayList;
import java.util.List;

public class ReturnMessage {
	public enum MessageType {
		Info, Success, Warning, Error
	}

	private String key = "";
	private String title = "";
	private String message = "";
	private MessageType type = MessageType.Info;

	private List<ReturnMessage> messageList = new ArrayList<ReturnMessage>();

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public List<ReturnMessage> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<ReturnMessage> messageList) {
		this.messageList = messageList;
	}
}
