package com.codingshuttle.om.school.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(nullable = false)
    private Long fees ;
    @Column(nullable = false)
    private LocalDate dateOfAdmission ;
    @OneToOne
    @JoinColumn(nullable = false,unique = true)
    private Student student ;
}
