package com.practice.om.blogApp.controller;

import com.practice.om.blogApp.dto.NoteDto;
import com.practice.om.blogApp.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService ;

    @GetMapping(path = "/{noteId}")
    public ResponseEntity<NoteDto> getNoteById(@RequestParam(name = "noteId") Long id) {
        NoteDto noteDto = noteService.getNoteById(id) ;
        return ResponseEntity.ok(noteDto) ;
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> getAllNotes() {
        List<NoteDto> noteDtoList = noteService.getAllNotes() ;
        return ResponseEntity.ok(noteDtoList) ;
    }

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDto) {
        NoteDto createdNote = noteService.createNote(noteDto) ;
        return ResponseEntity.ok(createdNote) ;
    }

}
