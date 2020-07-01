package com.codeup.blog.Controllers;

import com.codeup.blog.Services.EmailService;
import com.codeup.blog.daos.PostsRepository;
import com.codeup.blog.daos.UsersRepository;
import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private PostsRepository postsDao;
    private UsersRepository usersDao;
    private final EmailService emailService;

    public PostController(PostsRepository postsRepository, UsersRepository usersRepository, EmailService emailService){
        this.postsDao = postsRepository;
        this.usersDao = usersRepository;
        this.emailService = emailService;
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
    //we dont need a req param for every param.
    public String save(@ModelAttribute Post postToBeSaved){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postToBeSaved.setOwner(currentUser);
        Post savedPost= postsDao.save(postToBeSaved);
        emailService.prepareAndSend(savedPost,"A new post has been posted", "A Post has been created with an id of: " + savedPost.getId());
        return "redirect:/posts/" +savedPost.getId();
    }

    @GetMapping("posts/{id}/edit")
    public String showEditForm(Model model, @PathVariable long id){
        //find a post
        Post postToEdit = postsDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("posts/{id}/edit")
    public String update(@ModelAttribute Post postToEdit){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postToEdit.setOwner(currentUser);
        // save the changes
        postsDao.save(postToEdit);// update ads set title = ? where id = ?
        return "redirect:/posts/" + postToEdit.getId();
    }



    @DeleteMapping("/posts/{id}")
    @ResponseBody
    public String destroy(@PathVariable long id){
        postsDao.deleteById(id);
        return "ad deleted";
    }


}
