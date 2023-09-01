package com.ems.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.ems.framework.ExcelGenerator;
import com.ems.framework.ExcelUtils;
import com.ems.framework.MrBean;
import com.ems.framework.ServerSession;
import com.ems.mgr.service001Mgr;
import com.ems.share.EmployeeData;
import com.ems.share.EmployeeListDataSet;
import com.ems.share.Pager;
import com.ems.share.Result;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;


@Path("/service001")
public class service001 {
	@Context
	static
	HttpServletRequest request;
	@Context
	HttpServletResponse response;
	@javax.ws.rs.core.Context
	static ServletContext context;
	public static String userid = "";
	public static String username = "";
	public static String userpsw = "";

	private static MrBean getUser() {
		ServerSession.serverPath = request.getServletContext().getRealPath("/") + "/";
		MrBean user = new MrBean();
		user.getUser().setOrganizationID("001");
		user.getUser().setUserId(userid);
		user.getUser().setUserName(username);
		user.getUser().setPassword(userpsw);
		return user;
	}


	@POST
	@Path("saveEmployee")
	@Produces(MediaType.APPLICATION_JSON)
	public Result save(EmployeeData data) throws IOException, SQLException {
		Result res = new Result();
		res = service001Mgr.saveEmployee(data,getUser());
		return res;
	}
	
	
    @POST
    @Path("uploadImage")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> uploadImage(
            @FormDataParam("image") InputStream imageInputStream,
            @FormDataParam("image") FormDataContentDisposition imageDetail) {

        Map<String, String> response = new HashMap<>();
        String fileName = service001Mgr.uploadProfileImage(imageInputStream, imageDetail.getFileName(),getUser());

        if (fileName != null) {
            response.put("img", fileName);
            return response;
        } else {
            return null;
        }
    }
    
//
//	@POST
//	@Path("updateEmployee")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Result update(EmployeeData data) throws IOException, SQLException {
//		Result res = new Result();
//		res = service001Mgr.updateEmployee(data, getUser());
//		return res;
//	}
//	
	
	
	@GET
	@Path("getEmployeeBySyskey")
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeData getEmployeeBySyskey() throws IOException, SQLException {
		EmployeeData res = new EmployeeData();
		String syskey = request.getParameter("syskey");
		if (!syskey .equalsIgnoreCase(null)  && !syskey .equalsIgnoreCase("") ) {
			res = service001Mgr.getEmployeeBySyskey(syskey, getUser());
		}
		return res;
	}
	

	@POST
	@Path("getEmployeeList")
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeListDataSet getEmployelist(Pager pdata) throws IOException, SQLException {
		EmployeeListDataSet res = new EmployeeListDataSet();
		res = service001Mgr.getEmployeeList(pdata, getUser());
		return res;
	}
	
	

	@DELETE
	@Path("deleteEmployee")
	@Produces(MediaType.APPLICATION_JSON)
	public Result deletetEmployee() throws IOException, SQLException {
		Result res = new Result();
		String syskey = request.getParameter("syskey");
		if (!syskey .equalsIgnoreCase(null)  && !syskey .equalsIgnoreCase("") ) {
			res = service001Mgr.deleteEmployee(syskey, getUser());
		} else {
			res.setState(false);
			res.setMsgDesc("Can't Delete");
		}
		return res;
	}
	
    @GET
    @Path("getImage")
    @Produces("image/*") 
    public Response getImage(@QueryParam("name") String imageName) {
        String path = "C:\\Users\\Dell-593\\workspace\\EmployeeManagementSystem\\WebContent\\Upload\\";

        try {
            File imageFile = new File(path + imageName);
            if (imageFile.exists()) {
                InputStream imageStream = new FileInputStream(imageFile);
                return Response.ok(imageStream).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
	    

  
//	@GET
//	@Path("getEmployeeData")
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<EmployeeData> getEmployelist() throws IOException, SQLException {
//		ArrayList<EmployeeData> res = new ArrayList<EmployeeData>();
//		res = service001Mgr.getEmployeeData(getUser());
//		return res;
//	}
	
    @GET
    @Path("export")
    @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public Response exportToExcelFile(@Context HttpHeaders headers) throws IOException {
            
//        	response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//	        
//        	DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
//	        String currentDateTime = dateFormatter.format(new Date());
//
//	        String headerKey = "Content-Disposition";
//	        String headerValue = "attachment; filename=employee_" + currentDateTime + ".xlsx";
//
//	        response.setHeader(headerKey, headerValue);

	        List<EmployeeData> employeeData = service001Mgr.getEmployeeData(getUser());

	        ExcelGenerator generator = new ExcelGenerator(employeeData);
	        return generator.generateExcelFile();
    }	    
	        


    
    @POST
    @Path("/import")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Result importExcel(@Context HttpServletRequest request, @FormDataParam("file") InputStream fileInputStream) throws IOException {
        ServletContext context = request.getServletContext();
        Result res = ExcelUtils.parseExcelFile(fileInputStream, request, context);
        return res;
    }
    
}


	    
//  @POST
//  @Path("/import")
//  @Consumes(MediaType.MULTIPART_FORM_DATA)
//  public Result importExcel(@FormDataParam("file") InputStream fileInputStream) throws IOException {
//  	Result res = new Result();
//	    res=ExcelUtils.parseExcelFile(fileInputStream);	
//      return res;
//      
//  }
    
//static class ExcelUtils {	
//	
//	public static Result parseExcelFile(InputStream inputStream) throws IOException {
//	    Workbook workbook = new XSSFWorkbook(inputStream);
//	    Sheet sheet = workbook.getSheetAt(0);
//
//	    Map<String, EmployeeData> employeeMap = new HashMap<>();
//
//	    for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
//	        Row row = sheet.getRow(rowIndex);
//
//	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	        
//	        String employeeId = row.getCell(1).getStringCellValue();
//	        EmployeeData employee = employeeMap.getOrDefault(employeeId, new EmployeeData());
//	        
//	      
//
//	        employee.setEmployeeId(employeeId);
//	        employee.setName(row.getCell(2).getStringCellValue());
//	        employee.setPosition(row.getCell(3).getStringCellValue());
//	        employee.setDepartment(row.getCell(4).getStringCellValue());
//	        employee.setAddress(row.getCell(5).getStringCellValue());
//	        employee.setNrc(row.getCell(6).getStringCellValue());
//	        
//	        String dobString=row.getCell(7).getStringCellValue();
//        
//	        employee.setDob(dobString);
//	        
//	        employee.setGender(row.getCell(8).getStringCellValue());
//	        employee.setFatherName(row.getCell(9).getStringCellValue());
//	        employee.setLicense(row.getCell(10).getStringCellValue());
//	        employee.setTaxNo(row.getCell(11).getStringCellValue());
//	        employee.setImage(row.getCell(12).getStringCellValue());
//
//	        EducationDataList education = new EducationDataList();
//	        education.setType(row.getCell(13).getStringCellValue());
//	        education.setName(row.getCell(14).getStringCellValue());
//	        employee.getEducation().add(education);
//
//	        employeeMap.put(employeeId, employee);
//	    }	 
//	    
//	    
//
//	    Result res = new Result();
//
//	    for (EmployeeData employee : employeeMap.values()) {
//	    	
//	        res =service001Mgr.saveEmployee(employee, getUser()); 
//	    }
//	    return res;
//	}
//	
// }

//}   
















	
