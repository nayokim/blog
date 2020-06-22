package com.codeup.blog.Controllers;

import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

//    @GetMapping("/posts") another way doGet is on the line below Do not do both getmapping and requestmapping
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String allPosts(Model model){
        ArrayList<Post> allPosts = new ArrayList<>();
        allPosts.add(new Post("1", "test-1"));
        allPosts.add(new Post("2", "test-2"));
        allPosts.add(new Post("3", "test-3"));
        allPosts.add(new Post("4", "test-4"));

        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable long id, Model model){

        Post post = new Post("Hello World", "This is my first post");

        model.addAttribute("post", post);

        return "posts/show";
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
