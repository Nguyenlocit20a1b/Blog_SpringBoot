package com.example.blogdemospringboot.iservice;

import com.example.blogdemospringboot.entities.Post;
import com.example.blogdemospringboot.requestAPI.PostRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPost {
    List<Post> get3Post ();
    List <Post> getAllPost();

    Post createPost(@RequestBody PostRequest post) throws Exception;


    Post updatePost(UUID id , Post post) throws Exception;

    void deletePost(UUID postId) throws Exception;

    Optional<Post> getById(UUID id);
}
