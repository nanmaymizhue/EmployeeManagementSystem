package com.ems.share;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Result {
	public Result() {
		clearProperties();
	}

	private boolean state = false;
	private String msgCode = "";
	private String msgDesc = "";
	
	
	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsgDesc() {
		return msgDesc;
	}

	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}


	private void clearProperties() {
		state = false;
		msgCode = "";
		msgDesc = "";		
		
	}
}
