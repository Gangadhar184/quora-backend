package com.example.quora.services;

import com.example.quora.dtos.CommentDTO;
import com.example.quora.models.Answer;
import com.example.quora.models.Comment;
import com.example.quora.models.User;
import com.example.quora.repositories.AnswerRepository;
import com.example.quora.repositories.CommentsRepository;
import com.example.quora.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentsRepository commentsRepository;

    private AnswerRepository answerRepository;

    private UserRepository userRepository;

    public CommentService(CommentsRepository commentsRepository, AnswerRepository answerRepository, UserRepository userRepository) {
        this.commentsRepository = commentsRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }

    public List<Comment> getCommentsByAnswerId(Long answerId, int offset, int limit){
        return commentsRepository.findByAnswerId(answerId, PageRequest.of(offset, limit)).getContent();
    }

    public List<Comment> getRepliesByCommentId(Long commentId, int offset, int limit) {
        return commentsRepository.findByParentCommentId(commentId, PageRequest.of(offset, limit)).getContent();
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentsRepository.findById(id);
    }

    public Comment createComment(CommentDTO commentDTO) {
        Comment comments = new Comment();
        comments.setContent(commentDTO.getContent());

        // user
        Optional<User> user = userRepository.findById(commentDTO.getUserId());
        user.ifPresent(comments::setUser);

        // we try to find the corresponding answer from commentDTO and set the answer in the comment
        Optional<Answer> answer = answerRepository.findById(commentDTO.getAnswerId());
        answer.ifPresent(comments::setAnswer);

        if(commentDTO.getParentCommentId() != null) {
            Optional<Comment> parentComment = commentsRepository.findById(commentDTO.getParentCommentId()) ;
            parentComment.ifPresent(comments::setParentComment);
        }
        return commentsRepository.save(comments);
    }

    public void deleteComment(Long id){
        commentsRepository.deleteById(id);
    }

}
