package com.quizquiz.message;

public class ChannelSelect {

	private String opCode;
	private String joinChannel;
	private String errCode;
	private String errMsg;
	
	public ChannelSelect() { }
	
	public ChannelSelect(byte[] bData) throws Exception {
		int position = 0;
		
		byte[] bOPCode = new byte[2];
		byte[] bJoinChannel = new byte[1];
		byte[] bErrCode = new byte[2];
		byte[] bErrMsg = new byte[10];
		
		System.arraycopy(bData, position, bOPCode, 0, bOPCode.length);
		this.opCode = new String(bOPCode, "euc-kr");
		
		System.arraycopy(bData, position += bOPCode.length, bJoinChannel, 0, bJoinChannel.length);
		this.joinChannel = new String(bJoinChannel, "euc-kr");
		
		System.arraycopy(bData, position += bJoinChannel.length, bErrCode, 0, bErrCode.length);
		this.errCode = new String(bErrCode, "euc-kr");
		
		System.arraycopy(bData, position += bErrCode.length, bErrMsg, 0, bErrMsg.length);
		this.errMsg = new String(bErrMsg, "euc-kr");		
	}

	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	public String getJoinChannel() {
		return joinChannel;
	}

	public void setJoinChannel(String joinChannel) {
		this.joinChannel = joinChannel;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		return "ChannelSelect [opCode=" + opCode + ", joinChannel=" + joinChannel + ", errCode=" + errCode + ", errMsg="
				+ errMsg + "]";
	}
	
	public String toMessage() {
		return opCode + joinChannel + errCode + errMsg;
	}
}
