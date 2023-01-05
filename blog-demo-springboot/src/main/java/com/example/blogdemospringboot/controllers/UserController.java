package com.example.blogdemospringboot.controllers;

import com.example.blogdemospringboot.entities.User;
import com.example.blogdemospringboot.iservice.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1")
public class UserController {
    @Autowired
    private IUser iUser;

    // Show list user
    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        List<User> users = iUser.findAll();
        return users;
    }
    // Create User
    @PostMapping(path = "/users")
    public User createUser (@RequestBody User user) throws  Exception{
        User savedUser = null ;
        try {
            savedUser =  iUser.createUser(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return savedUser;
    }
    // Update user
    @PutMapping(path = "users/{userId}")
    public  User updateUser(@PathVariable(name = "userId")UUID userId , @RequestBody User user )  throws Exception{
        try{
            return iUser.updateUser(user , userId);
        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }

    }
    // Delete Category
    @DeleteMapping(path = "users/{userId}")
    public  String deleteUser (@PathVariable(name ="userId") UUID userId) throws  Exception {
        try {
            iUser.deleteUser(userId);
            return "Delete successfully";
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
