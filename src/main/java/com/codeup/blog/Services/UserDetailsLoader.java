package com.codeup.blog.Services;

import com.codeup.blog.daos.Roles;
import com.codeup.blog.daos.UsersRepository;
import com.codeup.blog.models.User;
import com.codeup.blog.models.UserWithRoles;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customerUserDetailsService")
public class UserDetailsLoader implements UserDetailsService {
    private final UsersRepository users; // users is referring to data access object
    private Roles roles;

    public UserDetailsLoader(UsersRepository users,Roles roles) {
        this.users = users;
        this.roles = roles;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No username: %s found", username));
        }
        return new UserWithRoles(user, roles.ofUserWith(username));
    }
}