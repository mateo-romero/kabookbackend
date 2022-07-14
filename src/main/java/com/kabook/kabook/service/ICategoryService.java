package com.kabook.kabook.service;

import com.kabook.kabook.model.Category;
import com.kabook.kabook.model.DTO.CategoryDTO;

import java.util.List;
import java.util.Optional;


public interface ICategoryService {
    Category addCategory(Category category);
    Category editCategory(Category category);
    void deleteCategory(Long id);
    Optional<Category> searchById(Long id);
    List<CategoryDTO> getAllCategories();
}

