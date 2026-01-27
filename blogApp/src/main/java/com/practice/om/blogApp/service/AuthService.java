package com.practice.om.blogApp.service;

import com.practice.om.blogApp.dto.LoginDto;
import com.practice.om.blogApp.dto.SignupDto;
import com.practice.om.blogApp.dto.UserDto;
import com.practice.om.blogApp.entity.User;
import com.practice.om.blogApp.exception.ResourceNotFoundException;
import com.practice.om.blogApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager ;
    private final JwtService jwtService;



    public String loginUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        ) ;
        User user = (User) authentication.getPrincipal() ;
        String token = jwtService.generateToken(user) ;
        return token;
    }


}
