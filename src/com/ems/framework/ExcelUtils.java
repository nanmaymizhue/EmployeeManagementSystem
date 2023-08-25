package com.ems.framework;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ems.mgr.service001Mgr;
import com.ems.share.EducationDataList;
import com.ems.share.EmployeeData;
import com.ems.share.Result;

public class ExcelUtils {
	
	public static String userid = "";
	public static String username = "";
	public static String userpsw = "";

    
    public static Result parseExcelFile(InputStream inputStream, HttpServletRequest request, ServletContext context) throws IOException {
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, EmployeeData> employeeMap = new HashMap<>();

        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String employeeId = row.getCell(1).getStringCellValue();
            EmployeeData employee = employeeMap.getOrDefault(employeeId, new EmployeeData());

            employee.setEmployeeId(employeeId);
            employee.setName(row.getCell(2).getStringCellValue());
            employee.setPosition(row.getCell(3).getStringCellValue());
            employee.setDepartment(row.getCell(4).getStringCellValue());
            employee.setAddress(row.getCell(5).getStringCellValue());
            employee.setNrc(row.getCell(6).getStringCellValue());

            String dobString = row.getCell(7).getStringCellValue();

            employee.setDob(dobString);

            employee.setGender(row.getCell(8).getStringCellValue());
            employee.setFatherName(row.getCell(9).getStringCellValue());
            employee.setLicense(row.getCell(10).getStringCellValue());
            employee.setTaxNo(row.getCell(11).getStringCellValue());
            employee.setImage(row.getCell(12).getStringCellValue());

            EducationDataList education = new EducationDataList();
            education.setType(row.getCell(13).getStringCellValue());
            education.setName(row.getCell(14).getStringCellValue());
            employee.getEducation().add(education);

            employeeMap.put(employeeId, employee);
        }

        Result res = new Result();

        for (EmployeeData employee : employeeMap.values()) {
            res = service001Mgr.saveEmployee(employee, getUser(request, context));
        }
        return res;
    }

	    private static MrBean getUser(HttpServletRequest request, ServletContext context) {
	        String serverPath = context.getRealPath("/") + "/";
	
	        MrBean user = new MrBean();       
	        user.getUser().setOrganizationID("001");
			user.getUser().setUserId(userid);
			user.getUser().setUserName(username);
			user.getUser().setPassword(userpsw);
			return user;
	
	    }
}

