package com.codingshuttle.om.school.service;

import com.codingshuttle.om.school.dto.AdmissionRecordDto;
import com.codingshuttle.om.school.entity.AdmissionRecord;
import com.codingshuttle.om.school.entity.Student;
import com.codingshuttle.om.school.exception.ResourceNotFoundException;
import com.codingshuttle.om.school.reopsitory.AdmissionRecordRepository;
import com.codingshuttle.om.school.reopsitory.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdmissionRecordService {
    final private ModelMapper modelMapper ;
    final private StudentRepository studentRepository ;
    final private AdmissionRecordRepository admissionRecordRepository ;

    @Transactional
    public AdmissionRecordDto assignAdmissionRecordToStudent(Long studentId, AdmissionRecordDto admissionRecordDto) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student NOT exist with id : "+studentId)) ;
        if(student.getAdmissionRecord() != null) throw new IllegalStateException("Student Have Already Admission Record") ;
        AdmissionRecord admissionRecord = modelMapper.map(admissionRecordDto, AdmissionRecord.class) ;
        admissionRecord.setStudent(student);
        admissionRecordRepository.save(admissionRecord) ;
        student.setAdmissionRecord(admissionRecord);
        return modelMapper.map(admissionRecord, AdmissionRecordDto.class) ;
    }

    @Transactional
    public Boolean removeAdmissionRecordFromStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student NOT exist with id : "+studentId)) ;
        AdmissionRecord admissionRecord = student.getAdmissionRecord() ;
        if(admissionRecord == null) throw new IllegalStateException("Student Have NOT any Admission Record") ;
        student.setAdmissionRecord(null);
        return true ;
    }

}
