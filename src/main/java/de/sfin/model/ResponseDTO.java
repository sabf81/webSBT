package de.sfin.model;

public class ResponseDTO {
	
	private int returnCode;
	private String messageText;
	
	public ResponseDTO(int returnCode, String msg) {
		this.returnCode = returnCode;
		this.messageText = msg;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	@Override
	public String toString() {
		return "ResponseDTO [returnCode=" + returnCode + ", messageText=" + messageText + "]";
	}
	
	

}
