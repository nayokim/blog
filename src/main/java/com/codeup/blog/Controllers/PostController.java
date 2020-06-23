package com.codeup.blog.Controllers;

import com.codeup.blog.daos.PostsRepository;
import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    private PostsRepository postsDao;

    public PostController(PostsRepository postsRepository){
        postsDao = postsRepository;
    }

//    @GetMapping("/posts") another way doGet is on the line below Do not do both getmapping and requestmapping
//    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @GetMapping("/posts")
    public String allPosts(Model model){
       List<Post> allPosts = postsDao.findAll();
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
    public String save(){
        Post newPost = new Post("Hello", "My name is Nayoung");
        postsDao.save(newPost);
        return "Create a new post";
    }

    @PutMapping("posts/{id}/edit")
    @ResponseBody
    public String update(@PathVariable long id){
        //find a post
        Post foundPost = postsDao.getOne(id); //select * from ads where id =?
        //edit the post
        foundPost.setTitle("Xbox series X");
        //save the changes
        postsDao.save(foundPost); //update posts set title = ? where id =?
        return "posted edited";
    }


    @DeleteMapping("/posts/{id}")
    @ResponseBody
    public String destroy(@PathVariable long id){
        postsDao.deleteById(id);
        return "ad deleted";
    }


}
