package com.javaguides.ems.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaguides.ems.entity.EmployeeCsv;
import com.javaguides.ems.repository.EmployeeCsvRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class EmployeeCsvService {

    @Autowired
    private EmployeeCsvRepository employeeCsvRepo;

    public void generateExcel(HttpServletResponse response) throws IOException {
        
        List<EmployeeCsv> employees = employeeCsvRepo.findAll();
        
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employee Info");
        HSSFRow row = sheet.createRow(0);
    
        row.createCell(1).setCellValue("ID");
        row.createCell(2).setCellValue("Email");
        row.createCell(3).setCellValue("First Name");
        row.createCell(4).setCellValue("Last Name");
    
        int dataRowIndex = 1;

        for(EmployeeCsv employee : employees) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(1).setCellValue(employee.getId());
            dataRow.createCell(2).setCellValue(employee.getEmail_id());
            dataRow.createCell(3).setCellValue(employee.getFirst_name());
            dataRow.createCell(4).setCellValue(employee.getLast_name());
            dataRowIndex ++;
        }

        ServletOutputStream ops =  response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }
    
}
