package com.codeup.blog.Controllers;

import com.codeup.blog.daos.PostsRepository;
import com.codeup.blog.daos.UsersRepository;
import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private PostsRepository postsDao;
    private UsersRepository usersDao;

    public PostController(PostsRepository postsRepository, UsersRepository usersRepository){
        postsDao = postsRepository;
        usersDao = usersRepository;
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
    public String show(@PathVariable long id, Model model){
        Post post = postsDao.getOne(id);
        model.addAttribute("postId", id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showForm(Model vmodel){
        //<form th:action="@{/posts/create}" th:method="post" th:object="${post}"> "" in addattribute needs to match th:object
        vmodel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String save(
            @RequestParam(value="title") String title,
            @RequestParam(value="body") String body
    ){
        User currentUser = usersDao.getOne(1L);
        Post newPost = new Post(title, body, currentUser,null,null);
        Post savedPost= postsDao.save(newPost);
        return "redirect:/posts/" +savedPost.getId();
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
