package com.example.quora.controllers;

import com.example.quora.dtos.CommentDTO;
import com.example.quora.models.Comment;
import com.example.quora.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/answer/{answerId}")
    public List<Comment> getCommentsByAnswerId(@PathVariable Long answerId, @RequestParam int offset, @RequestParam int limit) {
        return commentService.getCommentsByAnswerId(answerId, offset, limit);
    }

    @GetMapping("/comment/{commentId}")
    public List<Comment> getRepliesByCommentId(@PathVariable Long commentId, @RequestParam int offset, @RequestParam int limit) {
        return commentService.getRepliesByCommentId(commentId, offset, limit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentId(@PathVariable Long id) {
        Optional<Comment> comments = commentService.getCommentById(id);
        return comments.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping
    public Comment createComment(@RequestBody CommentDTO commentDTO) {
        return commentService.createComment(commentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
