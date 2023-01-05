package com.example.blogdemospringboot.services;

import com.example.blogdemospringboot.entities.Category;
import com.example.blogdemospringboot.entities.Post;
import com.example.blogdemospringboot.entities.User;
import com.example.blogdemospringboot.iservice.IPost;
import com.example.blogdemospringboot.repositories.CategoryRepository;
import com.example.blogdemospringboot.repositories.PostRepository;
import com.example.blogdemospringboot.repositories.UserRepository;
import com.example.blogdemospringboot.requestAPI.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService  implements IPost {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Post> get3Post() {
        List<Post> posts = postRepository.get3Post();
        return  posts;
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Override
    public Optional<Post> getById(UUID id) {
        return postRepository.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Post createPost( PostRequest postRequest) throws Exception {
        UUID categoryId = postRequest.getCategoryId();
        Optional<Category> optional = categoryRepository.findById(categoryId);
        Category category = null ;
        if(optional.isPresent()) {
            category = optional.get();
        } else {
            throw new Exception("Cannot find any category !");

        }
        UUID userId = postRequest.getUserId();
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = null ;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else  {
            throw  new Exception("Cannot find any User !!! ");
        }
        Post postEntity = new Post();
        postEntity.setId(UUID.randomUUID());
        postEntity.setPostTitle(postRequest.getTitle());
        postEntity.setContent(postRequest.getContent());
        postEntity.setCreateAt(LocalDate.now());
        postEntity.setCategory(category);
        postEntity.setUser(user);

        Post savePost = postRepository.save(postEntity);
        return savePost;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Post updatePost(UUID id, Post post) throws Exception {
        UUID categoryId = post.getCategory().getId();
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if(optionalCategory.isEmpty()) {
            throw new Exception("Cannot find any category !");
        }
        // Tim category ? isEmpty
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            throw new Exception("Cannot find any post !");
        }
        // Tim post ? isEmpty
        Post postInDB = optionalPost.get();
        postInDB.setPostTitle(post.getPostTitle());
        postInDB.setContent(post.getContent());
        postInDB.setUpdateAt(LocalDate.now());
        postInDB.setCategory(optionalCategory.get());

        return postRepository.save(postInDB);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePost(UUID id) throws Exception {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            throw  new Exception("Cannot find any Post");
        }
        postRepository.deleteById(id);
    }
}
