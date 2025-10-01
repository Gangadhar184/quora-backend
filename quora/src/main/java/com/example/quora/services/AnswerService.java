package com.example.quora.services;


import com.example.quora.dtos.AnswerDTO;
import com.example.quora.models.Answer;
import com.example.quora.models.Question;
import com.example.quora.models.User;
import com.example.quora.repositories.AnswerRepository;
import com.example.quora.repositories.QuestionRepository;
import com.example.quora.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;
    private UserRepository userRepository;

    public AnswerService(AnswerRepository answerRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public List<Answer> getAnswersByQuestionId(Long questionId, int offset, int limit) {
        return answerRepository.findByQuestionId(questionId, PageRequest.of(offset, limit)).getContent();
    }

    public Optional<Answer> getAnswerById(Long id) {
        return answerRepository.findById(id);
    }

    public Answer createAnswer(AnswerDTO answerDTO) {
        Answer answer = new Answer();
        answer.setContent(answerDTO.getContent());

        Optional<Question> question = questionRepository.findById(answerDTO.getQuestionId());
        question.ifPresent(answer::setQuestion);

        Optional<User> user = userRepository.findById(answerDTO.getUserId());
        user.ifPresent(answer::setUser);

        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }


}
