package com.codeup.blog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

//    @GetMapping("/posts") another way doGet is on the line below Do not do both getmapping and requestmapping
    @RequestMapping(value = "/ads", method = RequestMethod.GET)
    public String allPosts(){
        return "posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String showPost(@PathVariable long id){
        return "view an individual post with an id of : " + id ;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewCreatePost(){
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return"Create a new post";
    }


}
