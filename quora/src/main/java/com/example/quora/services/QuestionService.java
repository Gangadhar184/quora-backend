package com.example.quora.services;


import com.example.quora.dtos.QuestionDTO;
import com.example.quora.models.Question;
import com.example.quora.models.Tag;
import com.example.quora.models.User;
import com.example.quora.repositories.QuestionRepository;
import com.example.quora.repositories.TagRepository;
import com.example.quora.repositories.UserRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public List<Question> getQuestions(int offset, int limit) {
        return questionRepository.findAll(PageRequest.of(offset, limit)).getContent();
    }

    public Optional<Question> getQuestionById(Long id){
        return questionRepository.findById(id);
    }

    public Question createQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setTitle(questionDTO.getTitle());
        question.setContent(questionDTO.getContent());

        //set the user
        // if the user object is present then we set the user in question
        Optional<User> user = userRepository.findById(questionDTO.getUserId());
        user.ifPresent(question::setUser);

        Set<Tag> tags = questionDTO.getTagIds().stream()
        .map(tagRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(java.util.stream.Collectors.toSet());

        question.setTags(tags);
        return questionRepository.save(question);
    }
}
