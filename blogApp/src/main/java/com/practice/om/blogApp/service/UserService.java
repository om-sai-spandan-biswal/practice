package com.practice.om.blogApp.service;

import com.practice.om.blogApp.dto.SignupDto;
import com.practice.om.blogApp.dto.UserDto;
import com.practice.om.blogApp.entity.User;
import com.practice.om.blogApp.exception.ResourceNotFoundException;
import com.practice.om.blogApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository ;
    private final PasswordEncoder passwordEncoder ;
    private final ModelMapper modelMapper ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User with Email " + username + " is Not found"));
    }

    public UserDto signupUser(SignupDto signupDto) {
        Optional<User> user = userRepository.findByEmail(signupDto.getEmail());
        if(user.isPresent()) {
            throw  new BadCredentialsException("User With Email " + signupDto.getEmail() + " Is Already Exist") ;
        }
        User encodedUser = modelMapper.map(signupDto,User.class) ;
        encodedUser.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        User savedUser = userRepository.save(encodedUser) ;
        return modelMapper.map(savedUser, UserDto.class) ;
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with Id : "+id+" NOT found")) ;
        return modelMapper.map(user,UserDto.class) ;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with Id : "+id+" NOT found")) ;
    }


}
