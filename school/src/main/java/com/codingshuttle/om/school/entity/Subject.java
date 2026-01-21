package com.codingshuttle.om.school.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(nullable = false)
    private String title ;
    @Column(nullable = false)
    private Integer credits ;
    @ManyToOne
    private Professor professor ;
    @ManyToMany(mappedBy = "subjects")
    private List<Student> students = new ArrayList<>();
}
