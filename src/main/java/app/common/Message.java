package app.common;

public class Message {
	public enum MessageType {
		Info, Warning, Error
	}

	private String msgKey = "";
	private String msg = "";
	private MessageType msgType = MessageType.Info;

	public Message() {

	}

	public Message(String msg, MessageType msgType) {
		this("", msg, msgType);
	}

	public Message(String msgKey, String msg, MessageType msgType) {
		this.msgKey = msgKey;
		this.msg = msg;
		this.msgType = msgType;
	}

	public String getMsgKey() {
		return msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public MessageType getMsgType() {
		return msgType;
	}

	public void setMsgType(MessageType msgType) {
		this.msgType = msgType;
	}

}
