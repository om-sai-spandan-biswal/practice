package com.codingshuttle.om.school.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(nullable = false)
    private String name ;
    @Column(nullable = false)
    private String email ;
    @Column(nullable = false)
    private Integer age ;
    @ManyToMany(mappedBy = "professors")
    private List<Student> students = new ArrayList<>();
    @OneToMany(mappedBy = "professor")
    private List<Subject> subjects = new ArrayList<>() ;
}
