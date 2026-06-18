package com.ecom.learning.Sevice;

import java.util.List;
import java.util.Optional;

import com.ecom.learning.Model.Category;

public interface CategoryService {

    Category createCategory(Category category);

    List<Category> getAllCategory();
    Optional<Category> getCategoryById(Long id);
    Category updateCategory(Long id,Category category);
    void deleteCategory(Long id);
    
}
