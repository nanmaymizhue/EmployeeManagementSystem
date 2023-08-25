package com.ems.share;

import java.util.ArrayList;

public class EducationListDataSet {
	private String msgCode = "";
	private String msgDesc = "";
	private boolean state = false;
	private int total = 0;

	ArrayList<EducationDataList> data = new ArrayList<EducationDataList>();

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

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public ArrayList<EducationDataList> getData() {
		return data;
	}

	public void setData(ArrayList<EducationDataList> data) {
		this.data = data;
	}
	
	

}
