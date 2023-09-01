package com.ems.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import com.ems.framework.KeyGenerator;
import com.ems.share.EducationDataList;
import com.ems.share.EmployeeData;
import com.ems.share.EmployeeListDataSet;
import com.ems.share.Pager;
import com.ems.share.Result;


public class service001Dao {
	
	public static String datetoString() {
		String l_date = "";
		java.util.Date l_Date = new java.util.Date();
		l_date = getStartZero(4, String.valueOf(l_Date.getYear() + 1900))
				+ getStartZero(2, String.valueOf(l_Date.getMonth() + 1))
				+ getStartZero(2, String.valueOf(l_Date.getDate()));

		return l_date;
	}
	
	public static String getStartZero(int aZeroCount, String aValue) {
		while (aValue.length() < aZeroCount) {
			aValue = "0" + aValue;
		}
		return aValue;
	}
	
	
	
	 public static Result saveEmployee(EmployeeData data,Connection conn) throws SQLException, IOException {
		 Result result = new Result();
	        LocalDate currentDate = LocalDate.now();
	        String syskey = String.valueOf(KeyGenerator.generateSyskey());	        

	        String sql = "insert into employee (syskey, created_date, modified_date, record_status, employeeId, name, position, department, address, nrc, dob, gender, father_name, license, tax_no, image) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, syskey);
	        stmt.setDate(2, Date.valueOf(currentDate));
	        stmt.setDate(3, Date.valueOf(currentDate));
	        stmt.setInt(4, 1);
	        stmt.setString(5, data.getEmployeeId());
	        stmt.setString(6, data.getName());
	        stmt.setString(7, data.getPosition());
	        stmt.setString(8, data.getDepartment());
	        stmt.setString(9, data.getAddress());
	        stmt.setString(10, data.getNrc());
	        stmt.setDate(11, Date.valueOf(data.getDob())); 
	        stmt.setString(12, data.getGender());
	        stmt.setString(13, data.getFatherName());
	        stmt.setString(14, data.getLicense());
	        stmt.setString(15, data.getTaxNo());
	        stmt.setString(16, data.getImage());
	        
	        if (stmt.executeUpdate() > 0) { 
	            
	            ArrayList<EducationDataList> educationDataList = data.getEducation();
	            for (EducationDataList educationData : educationDataList) {
	                String educationSql = "INSERT INTO education ( created_date, modified_date, record_status, type, name, employee_key) VALUES (?,?,?,?,?,?)";
	               

	                PreparedStatement educationStmt = conn.prepareStatement(educationSql);
	              
	                educationStmt.setDate(1, Date.valueOf(currentDate));
	                educationStmt.setDate(2, Date.valueOf(currentDate));
	                educationStmt.setInt(3, 1);             
	                educationStmt.setString(4, educationData.getType());
	                educationStmt.setString(5, educationData.getName());
	                educationStmt.setString(6, syskey);

	                if(educationStmt.executeUpdate()>0){
		                result.setState(true);
			            result.setMsgDesc("Employee data saved successfully!");		            
			          
			            
	                }else {
						result.setState(false);
						result.setMsgDesc("Save Fail in education!");
					}
	            }

	        } else {
	            result.setState(false);
	            result.setMsgDesc("Failed to save employee data!");
	        }

	        return result;
	    }
	 
	 
	 public static Result updateEmployee(EmployeeData data, Connection conn) throws SQLException {
		    Result result = new Result();
		    LocalDate currentDate = LocalDate.now();

		    String employeeSyskey = data.getSyskey(); 

		    String employeeUpdateSql = "UPDATE employee SET modified_date=?, employeeId=?, name=?, position=?, department=?, address=?, nrc=?, dob=?, gender=?, father_name=?, license=?, tax_no=?, image=? WHERE syskey=?";
		    PreparedStatement employeeUpdateStmt = conn.prepareStatement(employeeUpdateSql);
		    employeeUpdateStmt.setDate(1, Date.valueOf(currentDate));
		    employeeUpdateStmt.setString(2, data.getEmployeeId());
		    employeeUpdateStmt.setString(3, data.getName());
		    employeeUpdateStmt.setString(4, data.getPosition());
		    employeeUpdateStmt.setString(5, data.getDepartment());
		    employeeUpdateStmt.setString(6, data.getAddress());
		    employeeUpdateStmt.setString(7, data.getNrc());
		    employeeUpdateStmt.setDate(8, Date.valueOf(data.getDob())); 
		    employeeUpdateStmt.setString(9, data.getGender());
		    employeeUpdateStmt.setString(10, data.getFatherName());
		    employeeUpdateStmt.setString(11, data.getLicense());
		    employeeUpdateStmt.setString(12, data.getTaxNo());
		    employeeUpdateStmt.setString(13, data.getImage());
		    employeeUpdateStmt.setString(14, employeeSyskey); // Use the correct employee syskey

		    if (employeeUpdateStmt.executeUpdate() > 0) {
		        String educationUpdateSql = "UPDATE education SET record_status = 4 WHERE employee_key = ?";
		        PreparedStatement educationUpdateStmt = conn.prepareStatement(educationUpdateSql);
		        educationUpdateStmt.setString(1, employeeSyskey);
		        educationUpdateStmt.executeUpdate();

		        ArrayList<EducationDataList> educationDataList = data.getEducation();
		        for (EducationDataList educationData : educationDataList) {
		            String educationSql = "INSERT INTO education (created_date, modified_date, record_status, type, name, employee_key) VALUES (?,?,?,?,?,?)";
		         

		            PreparedStatement educationStmt = conn.prepareStatement(educationSql);
	          
		            educationStmt.setDate(1, Date.valueOf(currentDate));
		            educationStmt.setDate(2, Date.valueOf(currentDate));
		            educationStmt.setInt(3, 1);
		            educationStmt.setString(4, educationData.getType());
		            educationStmt.setString(5, educationData.getName());
		            educationStmt.setString(6, employeeSyskey);

		            if (educationStmt.executeUpdate() > 0) {
		                result.setState(true);
		                result.setMsgDesc("Employee data updated successfully!");
		            } else {
		                result.setState(false);
		                result.setMsgDesc("Update Fail in education!");
		            }
		        }
		    } else {
		        result.setState(false);
		        result.setMsgDesc("Failed to update employee data!");
		    }

		    return result;
		}


	 
	 public static EmployeeData getEmployeeBySyskey(String syskey, Connection con) throws SQLException {
		    EmployeeData res = new EmployeeData();
		    ArrayList<EducationDataList> eduData = new ArrayList<>();

		    String sql = "select * from employee where record_status=1 and syskey =?";
		    PreparedStatement stmt = con.prepareStatement(sql);
		    stmt.setString(1, syskey);
		    ResultSet rs = stmt.executeQuery();

		    if (rs.next()) {
		        res.setSyskey(rs.getString("syskey"));
		        res.setEmployeeId(rs.getString("employeeId"));
		        res.setName(rs.getString("name"));
		        res.setPosition(rs.getString("position"));
		        res.setDepartment(rs.getString("department"));
		        res.setAddress(rs.getString("address"));
		        res.setNrc(rs.getString("nrc"));
		        res.setDob(rs.getString("dob"));
		        res.setGender(rs.getString("gender"));
		        res.setFatherName(rs.getString("father_name"));
		        res.setLicense(rs.getString("license"));
		        res.setTaxNo(rs.getString("tax_no"));
		        res.setImage(rs.getString("image"));
		        
		        String query = "select * from education where record_status=1 and employee_key=?";
		        PreparedStatement pst = con.prepareStatement(query);
		        pst.setString(1, syskey);
		        ResultSet r = pst.executeQuery();
		        
		        while (r.next()) {
		            EducationDataList data = new EducationDataList();
		       
		            data.setType(r.getString("type"));
		            data.setName(r.getString("name"));
		            eduData.add(data);
		        }
		    }
		    
		    if (!eduData.isEmpty()) {
		        res.setEducation(eduData);
		    }
		    return res;
		}

	


	 
	 public static EmployeeListDataSet getEmployeeList(Pager pdata, Connection conn) throws SQLException {

		 EmployeeListDataSet result = new EmployeeListDataSet();
		 
		 String whereclause = " WHERE record_status = 1";
		 if (!pdata.getSearchval().equalsIgnoreCase("")) {
		     whereclause += " AND (employeeId LIKE '%" + pdata.getSearchval() + "%' OR "
		     		+ "name LIKE '%" + pdata.getSearchval() + "%' "
		     		+ "OR name LIKE '%" + pdata.getSearchval() + "%'"
		     		+ "OR position LIKE '%" + pdata.getSearchval() + "%'"
		     		+ "OR department LIKE '%" + pdata.getSearchval() + "%')"
		     		+ "";
		 }
		 
			int end = pdata.getPagesize() * pdata.getCurrentpage(); 
			int start = (pdata.getCurrentpage() - 1) * pdata.getPagesize() + 1;	
			
			
			ArrayList<EmployeeData> datalist = new ArrayList<EmployeeData>();
				
			
			String sql = "SELECT * FROM employee" + whereclause + " ORDER BY employeeId desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, start - 1);
			stmt.setInt(2, end - start + 1);
			
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				EmployeeData data = new EmployeeData();
			
				data.setSyskey(res.getString("syskey"));
				data.setEmployeeId(res.getString("employeeId"));
				data.setName(res.getString("name"));
				data.setPosition(res.getString("position"));
				data.setDepartment(res.getString("department"));
				data.setAddress(res.getString("address"));
				data.setNrc(res.getString("nrc"));
				data.setDob(res.getString("dob"));
				data.setGender(res.getString("gender"));				
				data.setFatherName(res.getString("father_name"));
				data.setLicense(res.getString("license"));
				data.setTaxNo(res.getString("tax_no"));
				data.setImage(res.getString("image"));				
				
			    ArrayList<EducationDataList> educationList = getEducationDataForEmployee(data.getSyskey(), conn);
		        data.setEducation(educationList);
			        
				datalist.add(data);
			}
			if (datalist.size() > 0) {
				String query = "select count(syskey) as total from employee" + whereclause;
				PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					result.setTotal(rs.getInt("total"));
				}
				result.setData(datalist);
				result.setState(true);

			} else {
				result.setState(false);
				result.setMsgDesc("Data Not Found!");
			}
			return result;
		}
	
	 
	 public static ArrayList<EmployeeData> getEmployeeData(Connection conn) throws SQLException {		
			
			ArrayList<EmployeeData> datalist = new ArrayList<EmployeeData>();
			
			String sql = "select * from employee where record_status = 1"; 
	
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				EmployeeData data = new EmployeeData();			
				data.setSyskey(res.getString("syskey"));
				data.setEmployeeId(res.getString("employeeId"));
				data.setName(res.getString("name"));
				data.setPosition(res.getString("position"));
				data.setDepartment(res.getString("department"));
				data.setAddress(res.getString("address"));
				data.setNrc(res.getString("nrc"));
				data.setDob(res.getString("dob"));
				data.setGender(res.getString("gender"));				
				data.setFatherName(res.getString("father_name"));
				data.setLicense(res.getString("license"));
				data.setTaxNo(res.getString("tax_no"));
				data.setImage(res.getString("image"));				
				
			    ArrayList<EducationDataList> educationList = getEducationDataForEmployee(data.getSyskey(), conn);
		        data.setEducation(educationList);
			        
				datalist.add(data);
				
			}		
			return datalist;
		}
	 
	 
	 private static ArrayList<EducationDataList> getEducationDataForEmployee(String employeeSyskey, Connection conn) throws SQLException {
		    ArrayList<EducationDataList> educationList = new ArrayList<>();
		    
		    String educationSql = "SELECT * FROM education WHERE record_status=1 AND employee_key = ?";
		    PreparedStatement educationStmt = conn.prepareStatement(educationSql);
		    educationStmt.setString(1, employeeSyskey);
		    
		    ResultSet educationRes = educationStmt.executeQuery();
		    
		    while (educationRes.next()) {
		        EducationDataList educationData = new EducationDataList();
//		        educationData.setSyskey(educationRes.getString("syskey"));
		        educationData.setType(educationRes.getString("type"));
		        educationData.setName(educationRes.getString("name"));
		        educationList.add(educationData);
		    }		    
		    return educationList;
		}
	 
	    public static Result deleteEmployee(String syskey, Connection con) throws SQLException {
			Result res = new Result();
			String sql = "Update employee set record_status=4 where syskey =?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, syskey);
			if (stmt.executeUpdate() > 0) {
				String q = "Update education set record_status=4 where employee_key =?";
				PreparedStatement ps = con.prepareStatement(q);
				ps.setString(1, syskey);
				if (ps.executeUpdate() > 0) {
					res.setState(true);
					res.setMsgDesc("Delete Successfully!");
				} else {
					res.setState(false);
					res.setMsgDesc("Delete Fail!");
				}
				
			} else {
				res.setState(false);
				res.setMsgDesc("Delete Fail!");
			}
			return res;
		}

	    
		public static String saveImage(InputStream imageInputStream, String fileName, Connection conn) {
		 final String path = "C:\\Users\\Dell-593\\workspace\\EmployeeManagementSystem\\WebContent\\Upload\\";
//			 final String path = ServerSession.serverPath + "Upload";
			 try {
		            File destFile = new File(path + fileName);
		            FileUtils.copyInputStreamToFile(imageInputStream, destFile);
		        } catch (IOException e) {
		            e.printStackTrace();
		            return null;
		        }
		        return fileName;
		}
		
		

	
}
