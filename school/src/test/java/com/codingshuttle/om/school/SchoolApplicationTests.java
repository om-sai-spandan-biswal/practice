package com.codingshuttle.om.school;

import com.codingshuttle.om.school.entity.User;
import com.codingshuttle.om.school.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SchoolApplicationTests {

	@Autowired
	JwtService jwtService ;


	@Test
	void contextLoads() {
		String token = jwtService.generateToken(new User(10L,"om@email.com","pass")) ;
		System.out.println(token);
		String email = jwtService.getUserEmailFromToken(token) ;
		System.out.println(email);
	}

}
