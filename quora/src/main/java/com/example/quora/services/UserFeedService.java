package com.example.quora.services;


import com.example.quora.models.Question;
import com.example.quora.models.Tag;
import com.example.quora.models.User;
import com.example.quora.repositories.QuestionRepository;
import com.example.quora.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserFeedService {


    private UserRepository userRepository;

    private QuestionRepository questionRepository;

    public UserFeedService(UserRepository userRepository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public List<Question> getUserFeed(Long userId, int offset, int limit) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));

        Set<Long> tagIds = user.getFollowedTags().stream().map(Tag::getId).collect(Collectors.toSet());

        return questionRepository.findQuestionsByTags(tagIds, PageRequest.of(offset, limit)).getContent();
    }
}
