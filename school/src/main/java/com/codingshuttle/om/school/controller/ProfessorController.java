package com.codingshuttle.om.school.controller;

import com.codingshuttle.om.school.dto.ProfessorDto;
import com.codingshuttle.om.school.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/professor")
@RequiredArgsConstructor
public class ProfessorController {
    final private ProfessorService professorService ;

    @GetMapping(path = "/{profId}")
    public ResponseEntity<ProfessorDto> getProfessorById(@RequestParam(name = "profId") Long id) {
        return ResponseEntity.ok(professorService.getProfessorById(id)) ;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDto>> getAllProfessors() {
        return ResponseEntity.ok(professorService.getAllProfessors()) ;
    }
}
