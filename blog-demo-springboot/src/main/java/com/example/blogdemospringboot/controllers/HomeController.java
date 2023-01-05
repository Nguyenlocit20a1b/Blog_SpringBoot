package com.example.blogdemospringboot.controllers;

import com.example.blogdemospringboot.entities.Post;
import com.example.blogdemospringboot.iservice.IPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private IPost iPost;

    @GetMapping (path = "/")
    public  String HomePage (Model model) {
        List<Post> posts = iPost.get3Post() ;
        model.addAttribute("posts" , posts);
        return "home";
    }

}
