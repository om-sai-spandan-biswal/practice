package com.codingshuttle.om.school.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionRecordDto {
    private Long id ;
    @NotNull
    private Long fees ;
    @NotNull
    @Past
    private LocalDate dateOfAdmission ;
}
