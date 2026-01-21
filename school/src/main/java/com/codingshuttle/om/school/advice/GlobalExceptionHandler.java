package com.codingshuttle.om.school.advice;

import com.codingshuttle.om.school.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        ApiError apiError = ApiError
                .builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(apiErrorToApiResponse(apiError),apiError.getStatus()) ;
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> internalServerExceptionHandler(Exception exception) {
        ApiError apiError = ApiError
                .builder()
                .message(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return new ResponseEntity<>(apiErrorToApiResponse(apiError),apiError.getStatus()) ;
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        List<String> subErrors = exception
                .getAllErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .toList() ;

        ApiError apiError = ApiError
                .builder()
                .message("Input Validation Failed")
                .subError(subErrors)
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(apiErrorToApiResponse(apiError),apiError.getStatus()) ;
    }

    public ApiResponse<?> apiErrorToApiResponse(ApiError apiError) {
        return new ApiResponse<>(apiError) ;
    }


}
