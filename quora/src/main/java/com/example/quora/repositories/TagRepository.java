package com.example.quora.repositories;

import com.example.quora.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository <Tag, Long> {
}
