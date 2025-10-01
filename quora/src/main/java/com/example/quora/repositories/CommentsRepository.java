package com.example.quora.repositories;

import com.example.quora.models.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository< Comments, Long> {
    Page<Comments> findByAnswerId(Long answerId, Pageable pageable);
    Page<Comments> findParentCommentId(Long parentCommentId, Pageable pageable);
}
