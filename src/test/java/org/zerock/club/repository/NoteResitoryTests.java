package org.zerock.club.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.club.entity.ClubMember;
import org.zerock.club.entity.Note;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoteResitoryTests {
    @Autowired
    public NoteResitory noteResitory;

    @Test
    public void insertNotes() {
        IntStream.rangeClosed(1, 100)
                .forEach(i -> {
                    ClubMember writer = ClubMember.builder()
                            .email("user"+i+"@aaa.com")
                            .build();

                    Note note = Note.builder()
                            .title("Title.."+i)
                            .content("Content.."+i)
                            .writer(writer)
                            .build();

                    noteResitory.save(note);
                });
    }

    @Test
    public void testGetWithWriter() {
        Note note = noteResitory.getWithWriter(1L).orElseThrow();

        System.out.println("Note: "+note);
    }

    @Test
    public void testGetList() {
        List<Note> noteList = noteResitory.getList("user1@aaa.com");

        for(Note n : noteList) {
            System.out.println("Note: "+n);
        }
    }
}