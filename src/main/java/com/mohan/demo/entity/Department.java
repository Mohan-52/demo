package com.mohan.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departmentName;
    private String description;


    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference // in recursion avoid
    private List<Student> students=new ArrayList<>();

}
