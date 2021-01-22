package org.zerock.club.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.club.dto.NoteDto;
import org.zerock.club.service.NoteService;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/")
    public ResponseEntity<Long> register(@RequestBody NoteDto noteDto) {
        log.info("================ reigster ================");
        log.info(noteDto);

        Long num = noteService.register(noteDto);

        return ResponseEntity.ok(num);
    }

    @GetMapping(value = "/{num}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> read(@PathVariable("num") Long num){

        log.info("-----------read-------------------------------");
        log.info(num);
        return new ResponseEntity<>(noteService.get(num), HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NoteDto>> getList(String email){

        log.info("-----------getList-------------------------------");
        log.info(email);

        return new ResponseEntity<>(noteService.getAllWithWriter(email), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{num}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("num") Long num){

        log.info("-----------remove-------------------------------");
        log.info(num);

        noteService.remove(num);

        return new ResponseEntity<>("removed", HttpStatus.OK);

    }

    @PutMapping(value = "/{num}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@RequestBody NoteDto noteDto){

        log.info("-----------modify-------------------------------");
        log.info(noteDto);

        noteService.modify(noteDto);

        return new ResponseEntity<>("modified", HttpStatus.OK);

    }
}
