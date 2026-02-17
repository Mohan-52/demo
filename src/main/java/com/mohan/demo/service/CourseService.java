package com.mohan.demo.service;

import com.mohan.demo.dto.CourseDto;
import com.mohan.demo.dto.ResponseDto;
import com.mohan.demo.dto.StudentResDto;
import com.mohan.demo.entity.Course;
import com.mohan.demo.entity.Student;
import com.mohan.demo.exception.ResourceAlreadyExistEx;
import com.mohan.demo.exception.ResourceNotExistsEx;
import com.mohan.demo.repository.CourseRepository;
import com.mohan.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private StudentRepository studentRepo;


    private CourseDto mapToDto(Course course){
        CourseDto res=new CourseDto();
        res.setId(course.getId());
        res.setCourseName(course.getCourseName());
        res.setDescription(course.getDescription());

        return res;
    }





    public CourseDto createCourse(CourseDto request){
        if(courseRepo.existsByCourseName(request.getCourseName())){
            throw new ResourceAlreadyExistEx("Course with name "+request.getCourseName()+" already exists");
        }
        Course course=new Course();
        course.setCourseName(request.getCourseName());
        course.setDescription(request.getDescription());
        Course res= courseRepo.save(course);

        return mapToDto(res);
    }

    public List<CourseDto> getCourses(){
       return courseRepo.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    public ResponseDto enrollCourse(Long stdId, Long courseId){
        Student student=studentRepo.findById(stdId)
                .orElseThrow(()-> new ResourceNotExistsEx("Student with id "+stdId+" does not exists"));

        Course course=courseRepo.findById(courseId)
                .orElseThrow(()-> new ResourceNotExistsEx("Course with id "+courseId+" does not exists"));

        List<Student> alreadyEnrolled=course.getStudents();

        if(alreadyEnrolled.contains(student)){
            throw new ResourceAlreadyExistEx("You have already enrolled in this courses");
        }

        course.getStudents().add(student);

        courseRepo.save(course);

        return new ResponseDto("You have successfully enrolled in "+course.getDescription()+" course");

    }


    public ResponseDto dropCourse(Long stdId, Long courseId){
        Student student=studentRepo.findById(stdId)
                .orElseThrow(()-> new ResourceNotExistsEx("Student with id "+stdId+" does not exists"));

        Course course=courseRepo.findById(courseId)
                .orElseThrow(()-> new ResourceNotExistsEx("Course with id "+courseId+" does not exists"));

        List<Student> alreadyEnrolled=course.getStudents();

        if(!alreadyEnrolled.contains(student)){
            throw new ResourceAlreadyExistEx("You haven't enrolled in this courses");
        }

        course.getStudents().remove(student);

        courseRepo.save(course);

        return new ResponseDto("You have successfully drop from "+course.getDescription()+" course");

    }

    public List<CourseDto> getStudentCourses(Long stdId){
        return courseRepo.findCourseByStudentId(stdId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }




}
