package com.practice.om.blogApp.advice;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse<T> {
    private T data ;
    private ApiError error ;
    private LocalDateTime timeStamp ;

    public ApiResponse(){
        this.timeStamp = LocalDateTime.now() ;
    }

    public ApiResponse(ApiError error) {
        this() ;
        this.error = error ;
    }

    public ApiResponse(T data) {
        this() ;
        this.data = data ;
    }

}
