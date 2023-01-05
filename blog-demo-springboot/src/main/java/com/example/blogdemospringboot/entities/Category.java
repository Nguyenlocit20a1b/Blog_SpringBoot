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
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "id" , nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "category_name" , nullable = false)
    private String categoryName;

    @JsonBackReference("category_reference")
    @OneToMany(mappedBy = "category", targetEntity = Post.class , fetch = FetchType.LAZY)
    private List<Post> posts ;

}
