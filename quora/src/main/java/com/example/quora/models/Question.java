package com.example.quora.models;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Question extends BaseModel {
    private String title;
    private String content;

    @ManyToMany
    @JoinTable(
            name = "question_tags",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )

    private Set<Tag> tags;
    @JoinColumn(name = "user_id")
    private User user;
}

/*
 * any question can belong to multiple tags
 * one tag as many questions
 * one question belongs to many tags
 *
 *
 *
 * One question  belong to one user (one particular user)
 * one user can have many questions
 *
 */