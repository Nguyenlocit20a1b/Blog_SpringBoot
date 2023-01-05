package com.example.blogdemospringboot.controllers;

import com.example.blogdemospringboot.entities.Category;
import com.example.blogdemospringboot.iservice.ICategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1")
public class CategoryController {
    @Autowired
    private  ICategory iCategory;

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(path = "/categories",  consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public Category createCategory(@RequestBody Category category) throws  Exception{
        Category savedCategory = null ;
        try {
           savedCategory = iCategory.createCategory(category);
        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }
        return  savedCategory;
    }
    // findAll() tim kiem tat ca
    @GetMapping(path = "/categories")
    public List<Category> getAllCategories() {
        List<Category> categories = iCategory.findAll();
        return categories;
    }
    //  consumes : dinh dang kieu du lieu nhan ve
    // produce : dinh dang kieu du lieu day len
    //  HTTPStatus tra ve status code
    @ResponseStatus(value = HttpStatus.CREATED)
    @PutMapping(path = "/categories/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Category updateCategories(@PathVariable(name = "categoryId") UUID categoryId,
                                     @RequestBody Category category) {
        Category updateCategory = iCategory.updateCategory(category, categoryId);
        return updateCategory;
    }


    @DeleteMapping(path = "/categories/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteCategory (@PathVariable(name = "categoryId") UUID categoryId) throws  Exception {
        try {
            iCategory.deleteCategory(categoryId);
            return "Delete Post successfully";
        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }
    }
}
