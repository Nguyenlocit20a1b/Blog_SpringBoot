package com.example.blogdemospringboot.repositories;

import com.example.blogdemospringboot.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post,UUID > {
    @Query(value = "select * from posts LIMIT 3" , nativeQuery = true)
    List<Post> get3Post ();

}
