package com.mohan.demo.exception;

import com.mohan.demo.dto.ErrorResDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice // Controllers -> exception
public class GlobalExceptionHandler {

    public ResponseEntity<ErrorResDto> buildError(String path, HttpStatus status, Exception ex){
        ErrorResDto response=new ErrorResDto();
        response.setPath(path);
        response.setMessage(ex.getMessage());
        response.setTimeStamp(LocalDateTime.now());
        response.setStatus(status.value());
        response.setError(status.getReasonPhrase());

        return ResponseEntity.status(status).body(response);

    }

    @ExceptionHandler(ResourceAlreadyExistEx.class)
    public ResponseEntity<ErrorResDto> handleResourceAlreadyExits(Exception ex, HttpServletRequest request){
        return buildError(request.getRequestURI(), HttpStatus.CONFLICT,ex);

    }

    @ExceptionHandler(ResourceNotExistsEx.class)
    public ResponseEntity<ErrorResDto> handleNotExistsError(Exception ex, HttpServletRequest request){
        return buildError(request.getRequestURI(), HttpStatus.NOT_FOUND,ex);

    }
}
