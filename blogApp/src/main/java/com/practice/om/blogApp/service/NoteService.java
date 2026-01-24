package com.practice.om.blogApp.service;

import com.practice.om.blogApp.dto.NoteDto;
import com.practice.om.blogApp.entity.Note;
import com.practice.om.blogApp.exception.ResourceNotFoundException;
import com.practice.om.blogApp.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository ;
    private final ModelMapper modelMapper ;

    public List<NoteDto> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        return notes
                .stream()
                .map((note -> modelMapper.map(note,NoteDto.class)))
                .toList() ;
    }

    public NoteDto getNoteById(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note with Id : " + id + " is NOT exist")) ;
        return modelMapper.map(note,NoteDto.class) ;
    }

    public NoteDto createNote(NoteDto noteDto) {
        Note note = modelMapper.map(noteDto,Note.class) ;
        Note createdNote = noteRepository.save(note) ;
        return modelMapper.map(createdNote, NoteDto.class) ;
    }

}
