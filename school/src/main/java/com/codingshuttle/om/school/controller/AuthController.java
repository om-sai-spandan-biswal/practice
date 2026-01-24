package com.codingshuttle.om.school.controller;

import com.codingshuttle.om.school.dto.LoginDto;
import com.codingshuttle.om.school.dto.SignupDto;
import com.codingshuttle.om.school.dto.UserDto;
import com.codingshuttle.om.school.service.AuthService;
import com.codingshuttle.om.school.service.UserService;
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
    final private UserService userService ;
    final private AuthService authService ;


    @PostMapping("/signup")
    public ResponseEntity<UserDto> signupUser(@RequestBody SignupDto signupDto) {
        UserDto signupUserDto = userService.signupUser(signupDto) ;
        return ResponseEntity.ok(signupUserDto) ;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> loginUser(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        String token = authService.loginUser(loginDto);
        System.out.println(token);
        Cookie cookie = new Cookie("token",token) ;
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(loginDto) ;
    }


}
