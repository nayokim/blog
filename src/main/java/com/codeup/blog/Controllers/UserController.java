package com.codeup.blog.Controllers;

import com.codeup.blog.daos.Roles;
import com.codeup.blog.daos.UsersRepository;
import com.codeup.blog.models.User;
import com.codeup.blog.models.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UsersRepository users;
    private PasswordEncoder passwordEncoder;

    private Roles roles; // We need to inject the roles repository in order to save the role to the DB


    public UserController(UsersRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

    @PostMapping("/students/register")
    public String saveTheStudent(@ModelAttribute User student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        users.save(student);
        roles.save(UserRole.student(student));

        return "redirect:/login";
    }

    @PostMapping("/teachers/register")
    public String saveTheTeacher(@ModelAttribute User teacher) {
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        users.save(teacher);
        roles.save(UserRole.teacher(teacher));

        return "redirect:/login";
    }
}