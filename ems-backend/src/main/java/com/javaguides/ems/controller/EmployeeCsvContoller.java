package com.javaguides.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaguides.ems.service.EmployeeCsvService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class EmployeeCsvContoller {
    
    @Autowired
    private EmployeeCsvService employeeCsvService;

    @GetMapping("/excel")
    public void generateExcelReport(HttpServletResponse response) throws Exception {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=courses.xls";

        response.setHeader(headerKey, headerValue);

        employeeCsvService.generateExcel(response);
    }
}
