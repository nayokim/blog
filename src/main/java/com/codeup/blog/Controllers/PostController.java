package com.codeup.blog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String allPosts(){
        return "blog posts";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String showPost(@PathVariable long id){
        return "this is post number: " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewCreatePost(){
        return "form to create post goes here";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String creatPost(){
        return"Creat a new post";
    }


}
