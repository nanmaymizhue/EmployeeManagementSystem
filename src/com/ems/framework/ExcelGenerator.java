package com.ems.framework;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.ems.share.EducationDataList;
import com.ems.share.EmployeeData;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class ExcelGenerator {

    private List<EmployeeData> employees;
    private Workbook workbook;
	private Sheet sheet;

    public ExcelGenerator(List<EmployeeData> employees) {
        this.employees = employees;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Employee");
    }

    private void writeHeader() {  
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        style.setFont(font);
        createCell(row, 0, "No", style);
        createCell(row, 1, "Employee ID", style);
        createCell(row, 2, "Name", style);
        createCell(row, 3, "Position", style);
        createCell(row, 4, "Department", style);
        createCell(row, 5, "Address", style);
        createCell(row, 6, "Nrc", style);
        createCell(row, 7, "Dob", style);
        createCell(row, 8, "Gender", style);
        createCell(row, 9, "Father Name", style);
        createCell(row, 10, "License", style);
        createCell(row, 11, "TaxNo", style);
        createCell(row, 12, "Image", style);
        createCell(row, 13, "Education Type", style); 
        createCell(row, 14, "Education Name", style); 
    }
   
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {       
		sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if (valueOfCell instanceof LocalDate) {
        	  LocalDate localDate = (LocalDate) valueOfCell;
              String formattedDate = localDate.format(DateTimeFormatter.ofPattern("yyyy, MMM, dd"));
              cell.setCellValue(formattedDate);         
        } else if (valueOfCell instanceof Boolean) {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);
        for (EmployeeData record : employees) {           
			if (!record.getEducation().isEmpty()) {
                for (EducationDataList education : record.getEducation()) {
                    Row row = sheet.createRow(rowCount++);
                    int columnCount = 0;
                    createCell(row, columnCount++, rowCount - 1, style); 
                    createCell(row, columnCount++, record.getEmployeeId(), style);
                    createCell(row, columnCount++, record.getName(), style);
                    createCell(row, columnCount++, record.getPosition(), style);
                    createCell(row, columnCount++, record.getDepartment(), style);
                    createCell(row, columnCount++, record.getAddress(), style);
                    createCell(row, columnCount++, record.getNrc(), style);
                    createCell(row, columnCount++, record.getDob(), style);
                    createCell(row, columnCount++, record.getGender(), style);
                    createCell(row, columnCount++, record.getFatherName(), style);
                    createCell(row, columnCount++, record.getLicense(), style);
                    createCell(row, columnCount++, record.getTaxNo(), style);
                    createCell(row, columnCount++, record.getImage(), style);

                    // Education information
                    createCell(row, columnCount++, education.getType(), style);
                    createCell(row, columnCount++, education.getName(), style);
                }
            } else {
                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;
                createCell(row, columnCount++, rowCount - 1, style); 
                createCell(row, columnCount++, record.getEmployeeId(), style);
                createCell(row, columnCount++, record.getName(), style);
                createCell(row, columnCount++, record.getPosition(), style);
                createCell(row, columnCount++, record.getDepartment(), style);
                createCell(row, columnCount++, record.getAddress(), style);
                createCell(row, columnCount++, record.getNrc(), style);
                createCell(row, columnCount++, record.getDob(), style);
                createCell(row, columnCount++, record.getGender(), style);
                createCell(row, columnCount++, record.getFatherName(), style);
                createCell(row, columnCount++, record.getLicense(), style);
                createCell(row, columnCount++, record.getTaxNo(), style);
                createCell(row, columnCount++, record.getImage(), style);
            }
        }
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        ((Closeable) workbook).close();
        outputStream.close();
    }

}
