package org.zerock.club.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.club.dto.NoteDto;
import org.zerock.club.entity.Note;
import org.zerock.club.repository.NoteResitory;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteResitory noteResitory;


    @Override
    public Long register(NoteDto noteDto) {
        Note note = dtoToEntity(noteDto);

        log.info("----------------------------");
        log.info("note");

        noteResitory.save(note);

        return note.getNum();
    }

    @Override
    public NoteDto get(Long num) {
        return noteResitory.getWithWriter(num)
                .map(this::entityToDto)
                .orElseThrow();
    }

    @Override
    public void modify(NoteDto noteDto) {
        Long num = noteDto.getNum();

        Note note = noteResitory.findById(num).orElseThrow();

        note.chnageTitle(noteDto.getTitle());
        note.changeContent(noteDto.getContent());

        noteResitory.save(note);
    }

    @Override
    public void remove(Long num) {
        noteResitory.deleteById(num);
    }

    @Override
    public List<NoteDto> getAllWithWriter(String writerEmail) {
        List<Note> noteList = noteResitory.getList(writerEmail);

        return noteList.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
