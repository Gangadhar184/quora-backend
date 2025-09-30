package com.example.quora.repositories;

import com.example.quora.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository< Comments, Long> {
}
