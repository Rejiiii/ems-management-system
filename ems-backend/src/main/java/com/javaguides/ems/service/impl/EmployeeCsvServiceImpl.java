package com.javaguides.ems.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaguides.ems.entity.EmployeeCsv;
import com.javaguides.ems.repository.EmployeeCsvRepository;
import com.javaguides.ems.service.EmployeeCsvService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class EmployeeCsvServiceImpl implements EmployeeCsvService {

    @Autowired
    private EmployeeCsvRepository employeeCsvRepo;

    @Override
    public void generateExcel(HttpServletResponse response) throws IOException {

        List<EmployeeCsv> employees = employeeCsvRepo.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employee Info");

        // Create a custom blue color
        HSSFPalette palette = workbook.getCustomPalette();
        short blueColorIndex = palette.findSimilarColor(0, 24, 249).getIndex(); // RGB for light blue

        // Create a font with white color
        HSSFFont headerFont = workbook.createFont();
        headerFont.setColor(IndexedColors.WHITE.getIndex()); // Set font color to white
        headerFont.setBold(true);

        // Create a header style for the top row with a background color
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(blueColorIndex);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFont(headerFont); // Apply white font to header style

        // Set border for the header using BorderStyle.THICK
        headerStyle.setBorderTop(BorderStyle.THICK);
        headerStyle.setBorderBottom(BorderStyle.THICK);
        headerStyle.setBorderLeft(BorderStyle.THICK);
        headerStyle.setBorderRight(BorderStyle.THICK);

        // Apply light blue border color
        headerStyle.setTopBorderColor(blueColorIndex);
        headerStyle.setBottomBorderColor(blueColorIndex);
        headerStyle.setLeftBorderColor(blueColorIndex);
        headerStyle.setRightBorderColor(blueColorIndex);

        HSSFRow headerRow = sheet.createRow(0);

        headerRow.createCell(1).setCellValue("ID");
        headerRow.createCell(2).setCellValue("Email");
        headerRow.createCell(3).setCellValue("First Name");
        headerRow.createCell(4).setCellValue("Last Name");

        
        // Apply the header style to the header cells
        for (int i = 1; i <= 4; i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }
        
        // Set column widths
        sheet.setColumnWidth(2, 35 * 256); // Email
        sheet.setColumnWidth(3, 35 * 256); // First Name
        sheet.setColumnWidth(4, 35 * 256); // Last Name

        // Create a cell style for borders
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THICK);
        cellStyle.setBorderBottom(BorderStyle.THICK);
        cellStyle.setBorderLeft(BorderStyle.THICK);
        cellStyle.setBorderRight(BorderStyle.THICK);

        // Apply light blue border color to regular cells
        cellStyle.setTopBorderColor(blueColorIndex);
        cellStyle.setBottomBorderColor(blueColorIndex);
        cellStyle.setLeftBorderColor(blueColorIndex);
        cellStyle.setRightBorderColor(blueColorIndex);

        // Create a special cell style for the "ID" column
        HSSFCellStyle idCellStyle = workbook.createCellStyle();
        idCellStyle.setBorderTop(BorderStyle.THICK);
        idCellStyle.setBorderBottom(BorderStyle.THICK );
        idCellStyle.setBorderLeft(BorderStyle.THICK);
        idCellStyle.setBorderRight(BorderStyle.THICK  );

        // Apply light blue border color to the "ID" column
        idCellStyle.setTopBorderColor(blueColorIndex);
        idCellStyle.setBottomBorderColor(blueColorIndex);
        idCellStyle.setLeftBorderColor(blueColorIndex);
        idCellStyle.setRightBorderColor(blueColorIndex);

        // Set horizontal and vertical alignment to center for the "ID" column
        idCellStyle.setAlignment(HorizontalAlignment.CENTER);
        idCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // Write the employee data and apply borders to each cell
        int dataRowIndex = 1;
        for (EmployeeCsv employee : employees) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(1).setCellValue(employee.getId());
            dataRow.createCell(2).setCellValue(employee.getEmail_id());
            dataRow.createCell(3).setCellValue(employee.getFirst_name());
            dataRow.createCell(4).setCellValue(employee.getLast_name());

            // Apply the special style to the "ID" column
            dataRow.getCell(1).setCellStyle(idCellStyle);

            // Apply the border style to each cell in the row
            for (int i = 2; i <= 4; i++) {
                dataRow.getCell(i).setCellStyle(cellStyle);
            }
            dataRowIndex++;
        }

        // Write workbook to output stream
        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }

}
