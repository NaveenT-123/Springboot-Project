package com.ecom.learning.Sevice.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.learning.Model.Category;
import com.ecom.learning.Repository.CategoryRepository;
import com.ecom.learning.Sevice.CategoryService;



@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryrepository;


    public Category createCategory(Category category) {
        return categoryrepository.save(category);
    }


    public List<Category> getAllCategory() {
        return categoryrepository.findAll();
    }
    public Optional<Category>  getCategoryById(Long id){
    return categoryrepository.findById(id);
    }
    public Category updateCategory(Long id,Category category){
    Category existingCategory=categoryrepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not found"));
    existingCategory.setCategoryname(category.getCategoryname());
    return categoryrepository.save(existingCategory);
    }
   public void deleteCategory(Long id){
    categoryrepository.existsById(id);
    
   }
}