package com.javaguides.ems.service;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

public interface EmployeeCsvService {

    void generateExcel(HttpServletResponse response) throws IOException;

}
