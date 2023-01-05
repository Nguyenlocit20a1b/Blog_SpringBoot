package com.example.blogdemospringboot.iservice;

import com.example.blogdemospringboot.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUser {
    User createUser (User user) throws Exception;
    List<User> findAll();
    User updateUser (User  user, UUID userId) throws Exception;

    void deleteUser(UUID userId) throws Exception;

    Optional<User> findByEmail(String email);
}
