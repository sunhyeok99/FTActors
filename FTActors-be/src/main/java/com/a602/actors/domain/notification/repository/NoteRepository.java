package com.a602.actors.domain.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a602.actors.domain.notification.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
