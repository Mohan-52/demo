package com.mohan.demo.dto;

import lombok.Data;

@Data
public class StudentResDto {
    private Long  studentId;
    private String StudentName;
    private String email;
    private String departmentName;
}
