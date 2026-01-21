package com.codingshuttle.om.school.service;

import com.codingshuttle.om.school.dto.StudentDto;
import com.codingshuttle.om.school.entity.Student;
import com.codingshuttle.om.school.exception.ResourceNotFoundException;
import com.codingshuttle.om.school.reopsitory.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService {
    final private StudentRepository studentRepository ;
    final private ModelMapper modelMapper ;
    int PAGE_SIZE = 10 ;

    public StudentDto getStudentById(Long id) {
        isExist(id);
        Student student = studentRepository.findById(id).orElseThrow() ;
        return modelMapper.map(student,StudentDto.class) ;
    }

    public List<StudentDto> getAllStudent(String sortBy ,Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber,PAGE_SIZE,Sort.by(Sort.Direction.ASC,sortBy));
        List<Student> students = studentRepository.findAll(pageable).getContent() ;

        return students
                .stream()
                .map(student -> modelMapper.map(student,StudentDto.class))
                .toList() ;
    }

    public StudentDto createStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto,Student.class) ;
        studentRepository.save(student) ;
        return modelMapper.map(student,StudentDto.class) ;
    }

    public StudentDto updatePutById(Long id , StudentDto studentDto) {
        isExist(id);
        Student student = modelMapper.map(studentDto, Student.class) ;
        student.setId(id);
        studentRepository.save(student) ;
        return modelMapper.map(student,StudentDto.class) ;
    }

    public StudentDto updatePatchById(Long id, Map<String,Object> updates) {
        isExist(id);
        Student student = studentRepository.findById(id).orElseThrow() ;
        updates.forEach((field,update)-> {
            Field fieldToBeUpdate = ReflectionUtils.getRequiredField(Student.class,field) ;
            fieldToBeUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdate,student,update);
        });
        studentRepository.save(student) ;
        return modelMapper.map(student,StudentDto.class) ;
    }

    public Boolean deleteStudentById(Long id) {
        isExist(id);
        studentRepository.deleteById(id);
        return true ;
    }

    public void isExist(Long id) {
        if(!studentRepository.existsById(id)) throw new ResourceNotFoundException("Student NOT exist with id : "+id) ;
    }

}
