package com.a602.actors.domain.montage.repository;

import com.a602.actors.domain.montage.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MontageCommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByMontageId(Integer montageId);
    List<Comment> findByReferenceId(Integer referenceId);

}
