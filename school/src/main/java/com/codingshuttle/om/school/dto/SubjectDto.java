package com.codingshuttle.om.school.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {
    private Long id ;
    @NotBlank(message = "Subject Must have a Title")
    private String title ;
    @NotNull(message = "Subject Must have credits")
    private Integer credits ;
}
