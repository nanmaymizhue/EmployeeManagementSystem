package com.ems.framework;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class UserData {
	private long syskey;
	private long autokey;
	private String createdDate;
	private String modifiedDate;
	private String userId;
	private String userName;
	private String password;
	private String organizationID;
	private int recordStatus;
	private int syncStatus;
	private long syncBatch;
	private long usersyskey;
	private String sessionKey;
	private String t1;
	private String t2;
	private String t3;
	private String t4;
	private String t5;
	private String t6;
	private String t7;
	private String t8;
	private String t9;
	private String t10;
	private String t11;
	private String t12;
	private String t13;
	private String t14;
	private String t15;
	private String t16;
	private String t17;
	private String t18;
	private String t19;
	private String t20;
	private String t21;
	private String t22;
	private String t23;
	private String phoneno;
	private String address;
	private String photo;
	private String gender;
	private String dob;
	private long n1;
	private int n2;
	private long n3;
	private long n4;
	private int n5;
	private int n6;
	private int n7;
	private long n8;
	private long[] rolesyskey;
	//private PersonData person;
	private String name;
	private boolean isActive;
	private String msgCode;
	
	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserData() {
		clearProperties();
	}

//	public PersonData getPerson() {
//		return person;
//	}
//
//	public void setPerson(PersonData person) {
//		this.person = person;
//	}

	public long[] getRolesyskey() {
		return rolesyskey;
	}

	public void setRolesyskey(long[] result) {
		this.rolesyskey = result;
	}

	public long getSyskey() {
		return syskey;
	}

	public long getUsersyskey() {
		return usersyskey;
	}

	public void setUsersyskey(long usersyskey) {
		this.usersyskey = usersyskey;
	}

	public void setSyskey(long syskey) {
		this.syskey = syskey;
	}

	public long getAutokey() {
		return autokey;
	}

	public void setAutokey(long autokey) {
		this.autokey = autokey;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}

	public int getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(int syncStatus) {
		this.syncStatus = syncStatus;
	}

	public long getSyncBatch() {
		return syncBatch;
	}

	public void setSyncBatch(long syncBatch) {
		this.syncBatch = syncBatch;
	}

	public String getT1() {
		return t1;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public String getT3() {
		return t3;
	}

	public void setT3(String t3) {
		this.t3 = t3;
	}

	public String getT4() {
		return t4;
	}

	public void setT4(String t4) {
		this.t4 = t4;
	}

	public String getT5() {
		return t5;
	}

	public void setT5(String t5) {
		this.t5 = t5;
	}

	public String getT6() {
		return t6;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}

	public void setT6(String t6) {
		this.t6 = t6;
	}

	public String getT7() {
		return t7;
	}

	public void setT7(String t7) {
		this.t7 = t7;
	}

	public long getN1() {
		return n1;
	}

	public void setN1(long n1) {
		this.n1 = n1;
	}

	public int getN2() {
		return n2;
	}

	public void setN2(int n2) {
		this.n2 = n2;
	}

	public long getN3() {
		return n3;
	}

	public void setN3(long n3) {
		this.n3 = n3;
	}

	public long getN4() {
		return n4;
	}

	public void setN4(long n4) {
		this.n4 = n4;
	}

	public int getN5() {
		return n5;
	}

	public void setN5(int n5) {
		this.n5 = n5;
	}

	public int getN6() {
		return n6;
	}

	public void setN6(int n6) {
		this.n6 = n6;
	}

	public int getN7() {
		return n7;
	}

	public void setN7(int n7) {
		this.n7 = n7;
	}

	public long getN8() {
		return n8;
	}

	public void setN8(long n8) {
		this.n8 = n8;
	}
	
	public String getT8() {
		return t8;
	}

	public void setT8(String t8) {
		this.t8 = t8;
	}

	public String getT9() {
		return t9;
	}

	public void setT9(String t9) {
		this.t9 = t9;
	}

	public String getT10() {
		return t10;
	}

	public void setT10(String t10) {
		this.t10 = t10;
	}

	public String getT11() {
		return t11;
	}

	public void setT11(String t11) {
		this.t11 = t11;
	}

	public String getT12() {
		return t12;
	}

	public void setT12(String t12) {
		this.t12 = t12;
	}

	public String getT13() {
		return t13;
	}

	public void setT13(String t13) {
		this.t13 = t13;
	}

	public String getT14() {
		return t14;
	}

	public void setT14(String t14) {
		this.t14 = t14;
	}

	public String getT15() {
		return t15;
	}

	public void setT15(String t15) {
		this.t15 = t15;
	}

	public String getT16() {
		return t16;
	}

	public void setT16(String t16) {
		this.t16 = t16;
	}

	public String getT17() {
		return t17;
	}

	public void setT17(String t17) {
		this.t17 = t17;
	}

	public String getT18() {
		return t18;
	}

	public void setT18(String t18) {
		this.t18 = t18;
	}

	public String getT19() {
		return t19;
	}

	public void setT19(String t19) {
		this.t19 = t19;
	}

	public String getT20() {
		return t20;
	}

	public void setT20(String t20) {
		this.t20 = t20;
	}

	public String getT21() {
		return t21;
	}

	public void setT21(String t21) {
		this.t21 = t21;
	}

	public String getT22() {
		return t22;
	}

	public void setT22(String t22) {
		this.t22 = t22;
	}
	
	public String getT23() {
		return t23;
	}

	public void setT23(String t23) {
		this.t23 = t23;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	
	 
   
    
	protected void clearProperties() {
		this.syskey = 0;
		this.autokey = 0;
		this.createdDate = "";
		this.modifiedDate = "";
		this.userId = "";
		this.userName = "";
		this.recordStatus = 0;
		this.syncStatus = 0;
		this.syncBatch = 0;
		this.usersyskey = 0;
		this.t1 = "";
		this.t2 = "";
		this.t3 = "";
		this.t4 = "";
		this.t5 = "";
		this.t6 = "";
		this.t7 = "";
		this.t8 = "";
		this.t9 = "";
		this.t10 = "";
		this.t11 = "";
		this.t12 = "";
		this.t13 = "";
		this.t14 = "";
		this.t15 = "";
		this.t16 = "";
		this.t17 = "";
		this.t18 = "";
		this.t19 = "";
		this.t20 = "";
		this.t21 = "";
		this.t22 = "";
		this.t23 = "";
		this.n1 = 0;
		this.n2 = 0;
		this.n3 = 0;
		this.n4 = 0;
		this.n5 = 0;
		this.n6 = 0;
		this.n7 = 0;
		this.n8 = 0;
		//this.person = new PersonData();
		this.name = "";
		this.phoneno = "";
		this.address = "";
		this.photo = "";
		this.dob = "";
		this.gender = "";
		this.sessionKey="";
		this.sessionUserKey=0;
		this.isEmployee =false;
		this.msgCode = "";
	}
	
	

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	private long sessionUserKey;

	public long getSessionUserKey() {
		return sessionUserKey;
	}

	public void setSessionUserKey(long sessionUserKey) {
		this.sessionUserKey = sessionUserKey;
	}
	private boolean isEmployee;

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
}
