package com.ems.share;

import java.util.ArrayList;
import java.util.Date;

public class EmployeeData {
	private String syskey="";
	private String employeeId="";
	private String name="";
	private String position="";
	private String department="";
	private String address="";
	private String nrc="";
	private String dob="";
	private String gender="";
	private String fatherName="";
	private String license="";
	private String taxNo="";
	private String image="";
	
	ArrayList<EducationDataList> education = new ArrayList<EducationDataList>();

	public String getSyskey() {
		return syskey;
	}

	public void setSyskey(String syskey) {
		this.syskey = syskey;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ArrayList<EducationDataList> getEducation() {
		return education;
	}

	public void setEducation(ArrayList<EducationDataList> education) {
		this.education = education;
	}



	
	
}

