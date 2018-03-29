package com.quizquiz.message;

public class ChatSendAll {

	private String opCode;
	private String message;
	
	public ChatSendAll() { }
	
	public ChatSendAll(byte[] bData) throws Exception {
		int position = 0;
		
		byte[] bOPCode = new byte[2];
		byte[] bMessage = new byte[13];
		
		System.arraycopy(bData, position, bOPCode, 0, bOPCode.length);
		this.opCode = new String(bOPCode, "euc-kr");
		
		System.arraycopy(bData, position += bOPCode.length, bMessage, 0, bMessage.length);
		this.message = new String(bMessage, "euc-kr");		
	}

	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ChatSendAll [opCode=" + opCode + ", message=" + message + "]";
	}
	
	public String toMessage() {
		return opCode + message;
	}
}
