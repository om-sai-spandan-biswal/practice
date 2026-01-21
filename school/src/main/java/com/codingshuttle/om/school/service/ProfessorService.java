package com.codingshuttle.om.school.service;

import com.codingshuttle.om.school.dto.ProfessorDto;
import com.codingshuttle.om.school.entity.Professor;
import com.codingshuttle.om.school.exception.ResourceNotFoundException;
import com.codingshuttle.om.school.reopsitory.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    final private ProfessorRepository professorRepository ;
    final private ModelMapper modelMapper ;

    public ProfessorDto getProfessorById(@RequestParam Long id) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is No Professor with id : " + id)) ;
        return modelMapper.map(professor, ProfessorDto.class) ;
    }

    public List<ProfessorDto> getAllProfessors() {
        List<Professor> professors = professorRepository.findAll() ;
        return professors
                .stream()
                .map((professor -> modelMapper.map(professor, ProfessorDto.class)))
                .toList() ;
    }

    public ProfessorDto createProfessor(@RequestBody ProfessorDto professorDto) {
        Professor professor = professorRepository.save(modelMapper.map(professorDto, Professor.class)) ;
        return modelMapper.map(professor, ProfessorDto.class) ;
    }

    public ProfessorDto updatePutProfessorById(@RequestParam Long id, @RequestBody ProfessorDto professorDto) {
        professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is No Professor with id : " + id)) ;
        Professor professor = modelMapper.map(professorDto, Professor.class) ;
        professor.setId(id);
        return modelMapper.map(professorRepository.save(professor), ProfessorDto.class) ;
    }

    public ProfessorDto updatePatchProfessorById(@RequestParam Long id, @RequestBody Map<String,Object> updates) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is No Professor with id : " + id)) ;
        updates.forEach((field,value)-> {
            Field fieldToBeUpdate = ReflectionUtils.getRequiredField(Professor.class,field) ;
            fieldToBeUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdate,professor,value);
        });
        return modelMapper.map(professorRepository.save(professor),ProfessorDto.class) ;
    }

    public Boolean deleteProfessorById(@RequestParam Long id) {
        professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is No Professor with id : " + id)) ;
        professorRepository.deleteById(id);
        return true ;
    }

}
