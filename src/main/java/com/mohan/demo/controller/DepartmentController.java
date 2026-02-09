package com.mohan.demo.controller;

import com.mohan.demo.entity.Department;
import com.mohan.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @PostMapping
    public Department createDepartment(@RequestBody Department department){
        return service.createDepartment(department);
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable(value = "id") Long depId){
        return service.getDepartmentById(depId);
    }

    @GetMapping
    public List<Department> getAllDepartments(){
        return service.getAllDepartments();
    }

    @PutMapping
    public Department updateDepartment(@RequestParam(value = "id") Long depId, @RequestBody Department department){
        return service.updateDepartment(depId,department);
    }

    @DeleteMapping
    public void deleteDepartment(@RequestParam("id") Long id){
        service.deleteDepartment(id);
    }
}
