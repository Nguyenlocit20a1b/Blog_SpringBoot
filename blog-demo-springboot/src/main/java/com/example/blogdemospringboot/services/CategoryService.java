package com.example.blogdemospringboot.services;

import com.example.blogdemospringboot.entities.Category;
import com.example.blogdemospringboot.iservice.ICategory;
import com.example.blogdemospringboot.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService implements ICategory {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category createCategory(Category category) throws Exception {
      Category categoryEntity = new Category();
      categoryEntity.setId(UUID.randomUUID());
      categoryEntity.setCategoryName(category.getCategoryName());

      Category saveCategory = categoryRepository.save(categoryEntity);
        return categoryEntity;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Category updateCategory(Category category, UUID categoryId) {
        category.setId(categoryId);
        Category updatedCategory = categoryRepository.save(category);
        return updatedCategory;
    }

    @Override
    public void deleteCategory(UUID categoryId) throws  Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new Exception ("Cannot find any category");
        }
        categoryRepository.deleteById(categoryId);
    }
}
