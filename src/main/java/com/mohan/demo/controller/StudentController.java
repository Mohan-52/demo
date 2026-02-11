package com.mohan.demo.controller;

import com.mohan.demo.dto.StudentRegDto;
import com.mohan.demo.dto.StudentResDto;
import com.mohan.demo.entity.Student;
import com.mohan.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student registerStudent(@RequestBody StudentRegDto regDto){
        return studentService.registerStudent(regDto);
    }

    @GetMapping
    public List<StudentResDto> getAllStudents(){
        return studentService.getAllStudents();
    }
}
