package com.mohan.demo.service;

import com.mohan.demo.dto.StudentRegDto;
import com.mohan.demo.dto.StudentResDto;
import com.mohan.demo.entity.Department;
import com.mohan.demo.entity.Student;
import com.mohan.demo.exception.ResourceAlreadyExistEx;
import com.mohan.demo.exception.ResourceNotExistsEx;
import com.mohan.demo.repository.DepartmentRepository;
import com.mohan.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public StudentResDto mapToDto(Student student){
        StudentResDto response=new StudentResDto();
        response.setStudentId(student.getId());
        response.setStudentName(student.getName());
        response.setEmail(student.getEmail());
        return response;

    }

    public Student registerStudent(StudentRegDto request){
        Department department=departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(()-> new ResourceNotExistsEx("Department with id "+request.getDepartmentId()+" does not exists"));

        if(studentRepository.existsByEmail(request.getEmail())){
            throw new ResourceAlreadyExistEx("Student with email id "+request.getEmail()+ " already exist");
        }
        Student student=new Student();
        student.setDepartment(department);
        student.setName(request.getName());
        student.setEmail(request.getEmail());

        return studentRepository.save(student);

    }

    public List<StudentResDto> getAllStudents(){
        return studentRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    public List<StudentResDto> getStudentsByCourse(Long courseId){
        return studentRepository.findByCourses_Id(courseId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }
}
