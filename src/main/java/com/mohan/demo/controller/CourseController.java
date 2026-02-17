package com.mohan.demo.controller;

import com.mohan.demo.dto.CourseDto;
import com.mohan.demo.dto.ResponseDto;
import com.mohan.demo.dto.StudentResDto;
import com.mohan.demo.entity.Course;
import com.mohan.demo.entity.Student;
import com.mohan.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService service;


    @PostMapping
    public CourseDto createCourse(@RequestBody CourseDto request){
        return service.createCourse(request);
    }

    @GetMapping
    public List<CourseDto> getCourses(){
        return service.getCourses();
    }

    @PostMapping("/enroll/{stdId}/{courseId}")
    public ResponseDto enrollInCourse(@PathVariable Long stdId, @PathVariable Long courseId){
        return service.enrollCourse(stdId,courseId);
    }

    @PutMapping("/drop/{stdId}/{courseId}")
    public ResponseDto dropCourse(@PathVariable Long stdId, @PathVariable Long courseId){
        return service.dropCourse(stdId,courseId);
    }

    @GetMapping("/my/{std}")
    public List<CourseDto> getEnrolledStudents(@PathVariable Long std){
        return service.getStudentCourses(std);
    }
}
