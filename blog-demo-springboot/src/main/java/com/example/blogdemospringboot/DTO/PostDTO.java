package com.example.blogdemospringboot.DTO;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private UUID id ;
    private String title;
    private String content;
    private LocalDate createAt;
    private LocalDate updateAt;
    private String categoryName;
    private  String authorName;
}
