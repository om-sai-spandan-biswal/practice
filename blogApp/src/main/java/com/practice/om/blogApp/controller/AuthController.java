package com.practice.om.blogApp.controller;

import com.practice.om.blogApp.dto.LoginDto;
import com.practice.om.blogApp.dto.SignupDto;
import com.practice.om.blogApp.dto.UserDto;
import com.practice.om.blogApp.service.AuthService;
import com.practice.om.blogApp.service.UserService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService ;
    private final UserService userService;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> signupUser(@RequestBody SignupDto signupDto) {
        UserDto signedUpUser = userService.signupUser(signupDto) ;
        return ResponseEntity.ok(signedUpUser) ;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Boolean> loginUser(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        String token = authService.loginUser(loginDto) ;
        System.out.println(token);
        Cookie cookie = new Cookie("token",token) ;
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(true) ;
    }
}
