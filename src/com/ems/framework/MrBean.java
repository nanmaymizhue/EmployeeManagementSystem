package com.ems.framework;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class MrBean {
	private UserData user=new UserData();
	private String message = "";
	private String logoText = "";
	private boolean status = false;
	
	public UserData getUser() {
		return user;
	}
	public void setUser(UserData user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLogoText() {
		return logoText;
	}
	public void setLogoText(String logoText) {
		this.logoText = logoText;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean p) {
		status = p;
	}
	
	

}
