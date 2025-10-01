package com.example.quora.dtos;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String content;
    private Long answerId;
    private Long parentCommentId;
    private Long userId;
}
