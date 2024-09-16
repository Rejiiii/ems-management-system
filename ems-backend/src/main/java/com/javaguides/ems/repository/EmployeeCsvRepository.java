package com.javaguides.ems.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.javaguides.ems.entity.EmployeeCsv;

public interface EmployeeCsvRepository extends JpaRepository<EmployeeCsv, Serializable>{
    
}
