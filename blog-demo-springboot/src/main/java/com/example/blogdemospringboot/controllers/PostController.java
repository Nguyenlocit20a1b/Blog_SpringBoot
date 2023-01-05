package com.example.blogdemospringboot.controllers;

import com.example.blogdemospringboot.entities.Category;
import com.example.blogdemospringboot.entities.Post;
import com.example.blogdemospringboot.entities.User;
import com.example.blogdemospringboot.iservice.ICategory;
import com.example.blogdemospringboot.iservice.IPost;
import com.example.blogdemospringboot.iservice.IUser;
import com.example.blogdemospringboot.requestAPI.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class PostController {
    @Autowired
    private IPost iPost;

    @Autowired
    private ICategory iCategory;

    @Autowired
    private IUser iUser;
    @GetMapping(path = "/posts" )
    public String getAllPost(Model model) {
        List <Post> posts = iPost.getAllPost();
        System.out.println(posts.size());
        model.addAttribute("posts" , posts);
        return "posts";
    }

    @GetMapping("/detailPost/{id}")
    public String detailPost(@PathVariable UUID id , Model model) {
        Optional<Post> optionalPost = iPost.getById(id) ;
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post" , post);
            System.out.println(post.getCreateAt());
            return "postDetail";
        } else {
            return "404";
        }
    }

    @GetMapping(path = "/createPost")
    public String createPost (Model model) {
        List <Category> categories = iCategory.findAll();
        Optional<User> optionalUser = iUser.findByEmail("locnguyen@gmail.com");
        if (optionalUser.isPresent()) {
            PostRequest postRequest = new PostRequest();
            postRequest.setUserId(optionalUser.get().getId());
            model.addAttribute("categories", categories);
            model.addAttribute("post" , postRequest);
            System.out.println(postRequest.getPostId());
            return "createPost";
        } else {
            return "404";
        }
    }
    //views


    @PostMapping(path = "/createPost")
    public String createPost (@ModelAttribute PostRequest post )  throws  Exception {
        try {
            iPost.createPost(post);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        System.out.println();
        return "redirect:/posts" ;
    }
    @GetMapping("/postUpdate/{id}")
    public String PostUpdate (@PathVariable UUID id  , Model model) {
        List <Category> categories = iCategory.findAll();
        // find by id
        Optional<Post> optionalPost = iPost.getById(id) ;
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post" , post);
            model.addAttribute("categories" , categories);
            return "updatePost";
        } else {
            return "404";
        }
    }

    @PostMapping(path = "/postUpdate/{id}")
    public  String updatePost (@PathVariable UUID id , Post post) throws  Exception {
        System.out.println(id);
        try {
             iPost.updatePost(id , post);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "redirect:/detailPost/" + id;
    }

    // Delete Product with ID

    @GetMapping(path = "/delete/{id}")
    public String deletePost(@PathVariable UUID id) throws Exception {
        try {
            iPost.deletePost(id);
        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }
        return "redirect:/posts" ;
    }

}
