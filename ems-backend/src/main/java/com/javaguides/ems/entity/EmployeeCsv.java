package com.javaguides.ems.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="EMPLOYEES")
public class EmployeeCsv {

    @Id
    private Integer id;
    private String email_id;
    private String first_name;
    private String last_name;

    
}
