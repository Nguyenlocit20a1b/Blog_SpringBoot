package com.example.blogdemospringboot.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name ="posts")
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "post_title" , nullable = false , columnDefinition = "TEXT")
    private String postTitle;

    @Column(name = "content" , nullable = false , columnDefinition = "TEXT")
    private String content;

    @Column (name = "create_at" ,  nullable = false , columnDefinition = "TEXT")
    private LocalDate createAt;

    @Column(name = "image" )
    private String image;

    @Column (name = "update_at" , columnDefinition = "TEXT")
    private LocalDate updateAt;

    @ManyToOne(targetEntity = Category.class , fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id" , referencedColumnName = "id")
    private Category category;

    @ManyToOne(targetEntity = User.class , fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;

}
