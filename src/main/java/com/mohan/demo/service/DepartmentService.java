package com.mohan.demo.service;

import com.mohan.demo.entity.Department;
import com.mohan.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepo;


    public Department createDepartment(Department department){

        if(departmentRepo.existsByDepartmentName(department.getDepartmentName())){
            throw  new RuntimeException("Department with name "+department.getDepartmentName()+" already exists");
        }


        return departmentRepo.save(department);
    }

    public Department getDepartmentById(Long id){
       Department department=departmentRepo.findById(id)
               .orElseThrow(()-> new RuntimeException("Department with id "+id+" does not exists"));

       return department;
    }

    public List<Department> getAllDepartments(){
        return departmentRepo.findAll();
    }

    public Department updateDepartment(Long id,Department department){
        Department existingDepartment=departmentRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Department with id "+id+" does not exists"));

        existingDepartment.setDepartmentName(department.getDepartmentName());
        existingDepartment.setDescription(department.getDescription());

        return departmentRepo.save(existingDepartment);

    }

    public void deleteDepartment(Long id){
        Department existingDepartment=departmentRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Department with id "+id+" does not exists"));

        departmentRepo.delete(existingDepartment);
    }
}
