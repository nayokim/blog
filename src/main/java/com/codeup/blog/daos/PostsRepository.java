package com.codeup.blog.daos;

import com.codeup.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository <Post, Long> {

    // HQL
    @Query("from Post as a where a.title like %:term% or a.body like %:term%")
    List<Post> searchByTitle(@Param("term") String term);

    // query methods
    Post findFirstByTitle(String title); // select * from ads where title = ? limit 1

    Post findByTitle(String post_to_be_deleted);
}
