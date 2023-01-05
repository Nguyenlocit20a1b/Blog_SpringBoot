package com.example.blogdemospringboot.services;

import com.example.blogdemospringboot.entities.User;
import com.example.blogdemospringboot.iservice.IUser;
import com.example.blogdemospringboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUser {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User createUser(User user) throws Exception {
        User userEntity = new User();
        userEntity.setId(UUID.randomUUID());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        User savedUser = userRepository.save(userEntity);
        return savedUser;
    }

    // Show list
    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    //Update
    @Override
    public User updateUser(User user, UUID userId) throws Exception {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw  new Exception("Cannot find any user");
        }
        User userEntity = userOptional.get();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }

    @Override
    public void deleteUser(UUID userId) throws Exception {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw  new Exception("Cannot find any User");
        }
        userRepository.deleteById(userId);
    }
}
