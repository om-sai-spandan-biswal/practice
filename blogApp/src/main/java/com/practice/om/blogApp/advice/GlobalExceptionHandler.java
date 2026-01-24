package com.practice.om.blogApp.advice;

import com.practice.om.blogApp.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final View error;

    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        ApiError error = ApiError.builder()
                .message(exception.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(new ApiResponse<>(error),error.getHttpStatus()) ;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> invalidArgumentExceptionHandler(MethodArgumentNotValidException exception) {
        List<String> subErrors = exception
                .getAllErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .toList() ;
        ApiError error = ApiError.builder()
                .message("Invalid Argument Error")
                .subError(subErrors)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build() ;
        return new ResponseEntity<>(new ApiResponse<>(error),error.getHttpStatus()) ;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> internalServerExceptionHandler(Exception exception) {
        ApiError error = ApiError.builder()
                .message(exception.getMessage())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build() ;
        return new ResponseEntity<>(new ApiResponse<>(error),error.getHttpStatus()) ;
    }
}
