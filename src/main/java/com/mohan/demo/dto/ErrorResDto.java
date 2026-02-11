package com.mohan.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResDto {
    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String path;
    private String message;
}
