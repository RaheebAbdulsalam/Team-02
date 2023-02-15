package com.gamestation.ecommerce.service;

import com.gamestation.ecommerce.exception.ResourceNotFoundException;
import com.gamestation.ecommerce.model.Category;
import com.gamestation.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Integer id, Category categoryDetails) {
        Category category = getCategoryById(id);
        category.setName(categoryDetails.getName());
        category.setDescription(categoryDetails.getDescription());
        category.setImage(categoryDetails.getImage());

        Category updatedCategory = categoryRepository.save(category);
        return updatedCategory;
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}

