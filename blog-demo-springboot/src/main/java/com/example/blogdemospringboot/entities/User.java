package com.example.blogdemospringboot.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id" , nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @JsonBackReference("user_reference")
    @OneToMany(mappedBy = "user" , targetEntity = Post.class , fetch = FetchType.LAZY )
    private List <Post> posts;
}
