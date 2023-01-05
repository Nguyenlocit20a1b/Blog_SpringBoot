package com.example.blogdemospringboot.iservice;

import com.example.blogdemospringboot.entities.Category;

import java.util.List;
import java.util.UUID;

public interface ICategory {
    Category createCategory(Category category) throws Exception;
    List<Category> findAll();
    Category updateCategory(Category category, UUID categoryId);

    void deleteCategory(UUID categoryId) throws Exception;
}
