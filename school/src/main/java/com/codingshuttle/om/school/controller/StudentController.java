package com.codingshuttle.om.school.controller;

import com.codingshuttle.om.school.dto.StudentDto;
import com.codingshuttle.om.school.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/student")
@RequiredArgsConstructor
public class StudentController {

    final private StudentService studentService ;

    @GetMapping(path = "/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable(name = "studentId") Long id) {
        StudentDto studentDto = studentService.getStudentById(id) ;
        return ResponseEntity.ok(studentDto) ;
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudent(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber
    ) {
        List<StudentDto> studentDtoList = studentService.getAllStudent(sortBy,pageNumber) ;
        return ResponseEntity.ok(studentDtoList) ;
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto) {
        StudentDto createdStudentDto = studentService.createStudent(studentDto) ;
        return new ResponseEntity<>(createdStudentDto,HttpStatus.CREATED) ;
    }

    @PutMapping(path = "/{studentId}")
    public ResponseEntity<StudentDto> updatePutStudentById(@PathVariable(name = "studentId") Long id,@Valid @RequestBody StudentDto studentDto) {
        StudentDto updatedStudentDto = studentService.updatePutById(id,studentDto) ;
        return ResponseEntity.ok(updatedStudentDto) ;
    }

    @PatchMapping(path = "/{studentId}")
    public ResponseEntity<StudentDto> updatePatchStudentById(@PathVariable(name = "studentId") Long id, @RequestBody Map<String,Object> updates) {
        StudentDto updatedStudentDto = studentService.updatePatchById(id, updates) ;
        return ResponseEntity.ok(updatedStudentDto) ;
    }

    @DeleteMapping(path = "/{studentId}")
    public ResponseEntity<Boolean> deleteStudentById(@PathVariable(name = "studentId") Long id) {
        return ResponseEntity.ok(studentService.deleteStudentById(id)) ;
    }

}
