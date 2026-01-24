package com.practice.om.blogApp.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiError {
    private String message ;
    private List<String> subError ;
    private HttpStatus httpStatus ;
}
