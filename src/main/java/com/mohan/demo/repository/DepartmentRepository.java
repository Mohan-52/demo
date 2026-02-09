package com.mohan.demo.repository;

import com.mohan.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
        boolean existsByDepartmentName(String name);

       // Department

}
