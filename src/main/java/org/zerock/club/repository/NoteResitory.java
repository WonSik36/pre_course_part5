package org.zerock.club.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.club.entity.Note;

import java.util.List;
import java.util.Optional;

public interface NoteResitory extends JpaRepository<Note, Long> {

    @Query("select n from Note n join fetch n.writer where n.num = :num")
    Optional<Note> getWithWriter(@Param("num") Long num);

    @Query("select n from Note n join fetch n.writer where n.writer.email = :email")
    List<Note> getList(@Param("email") String email);
}
