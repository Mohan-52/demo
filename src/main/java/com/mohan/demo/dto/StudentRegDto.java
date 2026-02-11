package com.mohan.demo.dto;

import lombok.Data;

@Data
public class StudentRegDto {
    private String name;
    private String email;
    private Long departmentId;
}
