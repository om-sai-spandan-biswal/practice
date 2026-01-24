package com.codingshuttle.om.school.service;

import com.codingshuttle.om.school.dto.SignupDto;
import com.codingshuttle.om.school.dto.UserDto;
import com.codingshuttle.om.school.entity.User;
import com.codingshuttle.om.school.exception.ResourceNotFoundException;
import com.codingshuttle.om.school.reopsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository ;
    private final ModelMapper modelMapper ;
    private final PasswordEncoder passwordEncoder ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("User with email : "+username+" NOT found"));
    }

    public UserDto signupUser(SignupDto signupDto) {
        Optional<User> user = userRepository.findByEmail(signupDto.getEmail()) ;
        if(user.isPresent()) {
            throw new BadCredentialsException("User with email is Already Exist" + signupDto.getEmail()) ;
        }
        User createdUser = modelMapper.map(signupDto,User.class) ;
        createdUser.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        User passwordEncodedUser = userRepository.save(createdUser) ;
        return modelMapper.map(passwordEncodedUser, UserDto.class) ;
    }

    public User findUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User with Id : "+id+" NOT found")) ;
    }
}
