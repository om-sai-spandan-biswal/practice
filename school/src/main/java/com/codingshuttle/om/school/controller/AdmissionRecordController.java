package com.codingshuttle.om.school.controller;

import com.codingshuttle.om.school.dto.AdmissionRecordDto;
import com.codingshuttle.om.school.service.AdmissionRecordService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admissionRecord")
@RequiredArgsConstructor
public class AdmissionRecordController {

    final private AdmissionRecordService admissionRecordService ;

    @PostMapping(path = "/{studentId}")
    public AdmissionRecordDto assignAdmissionRecordToStudent(@PathVariable Long studentId,@RequestBody AdmissionRecordDto admissionRecordDto) {
        return admissionRecordService.assignAdmissionRecordToStudent(studentId, admissionRecordDto) ;
    }

    @DeleteMapping(path = "/{studentId}")
    public Boolean removeAdmissionRecordFromStudent(@PathVariable Long studentId) {
        return admissionRecordService.removeAdmissionRecordFromStudent(studentId) ;
    }
}
