package com.ems.mgr;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ems.dao.service001Dao;
import com.ems.framework.ConnAdmin;
import com.ems.framework.MrBean;
import com.ems.share.EmployeeData;
import com.ems.share.EmployeeListDataSet;
import com.ems.share.Pager;
import com.ems.share.Result;

public class service001Mgr {
	
	public static Result saveEmployee(EmployeeData data,MrBean user) throws IOException {

		Result res = new Result();
		try (Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID())) {
			if (conn != null) {
				
				if (data.getSyskey().equalsIgnoreCase("0") || data.getSyskey().equalsIgnoreCase("")) {
					res = service001Dao.saveEmployee(data,conn);	
					
				} else {
					res = service001Dao.updateEmployee(data,conn);
				}

			} else {
				res.setMsgDesc("Can't connect to database");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static Result updateEmployee(EmployeeData data, MrBean user) {

		Result res = new Result();
		try (Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID())) {
			if (conn != null) {
				res = service001Dao.updateEmployee(data,conn);		

			} else {
				res.setMsgDesc("Can't connect to database");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	
	public static EmployeeData getEmployeeBySyskey(String syskey, MrBean user) {
		EmployeeData res = new EmployeeData();
		try (Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID())) {
			if (conn != null) {
				res = service001Dao.getEmployeeBySyskey(syskey, conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;

	}
	
	public static EmployeeListDataSet getEmployeeList(Pager pdata, MrBean user) {

		EmployeeListDataSet res = new EmployeeListDataSet();
		try (Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID())) {
			if (conn != null) {

				res = service001Dao.getEmployeeList(pdata, conn);
			}else{
				res.setMsgDesc("Can't connect to Database");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	public static Result deleteEmployee(String syskey, MrBean user) {

		Result res = new Result();
		try (Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID())) {
			if (conn != null) {
				res = service001Dao.deleteEmployee(syskey, conn);
			} else {
				res.setMsgDesc("Can't connect to database");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}


	public static ArrayList<EmployeeData> getEmployeeData(MrBean user) {
		ArrayList<EmployeeData> res = new ArrayList<EmployeeData>();
		try (Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID())) {
			if (conn != null) {
				res = service001Dao.getEmployeeData(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static String uploadProfileImage(InputStream imageInputStream, String fileName, MrBean user) {
		
		Result res = new Result();
		try (Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID())) {
			if (conn != null) {
				
				return service001Dao.saveImage(imageInputStream, fileName,conn);

			} else {
				res.setMsgDesc("Can't connect to database");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	public static Object importEmployeeFromExcel(InputStream fileInputStream, MrBean user) {
//		
//		Result res = new Result();
//		try (Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID())) {
//			if (conn != null) {				
//				return service001Dao.importEmployee(fileInputStream,conn);
//
//			} else {
//				res.setMsgDesc("Can't connect to database");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return res;
//	}

}
