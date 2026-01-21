package com.codingshuttle.om.school.dto;

import jakarta.validation.constraints.Email;
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
public class ProfessorDto {
    private Long id ;
    @NotBlank(message = "Name of Professor should NOT be blank")
    private String name ;
    @Email(message = "email should be valid")
    @NotBlank(message = "email of Professor should NOT be blank")
    private String email ;
    @NotNull(message = "Age should NOT be null")
    private Integer age ;
}
