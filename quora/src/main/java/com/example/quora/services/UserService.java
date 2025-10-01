package com.example.quora.services;




import com.example.quora.dtos.UserDTO;
import com.example.quora.models.Tag;
import com.example.quora.models.User;
import com.example.quora.repositories.TagRepository;
import com.example.quora.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private TagRepository tagRepository;

    public UserService(UserRepository userRepository, TagRepository tagRepository) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(UserDTO userDto) { //to create a user we need user dto
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    //user can follow a particular tag
    public void followTag(Long userId, Long tagId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found")  );
        Tag tag = tagRepository.findById(tagId).orElseThrow(()->new RuntimeException("Tag not found") );
        user.getFollowedTags().add(tag);
        userRepository.save(user);
    }
}
