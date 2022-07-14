package com.kabook.kabook.service.Impl;

import com.kabook.kabook.model.Category;
import com.kabook.kabook.model.DTO.CategoryDTO;
import com.kabook.kabook.repository.ICategoryRepository;
import com.kabook.kabook.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class
CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;


    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category editCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);

    }
    @Override
    public Optional<Category> searchById(Long id){
        return categoryRepository.findById(id);
    } //este metodo no lo pide pero lo agregue para usarlo en el endpoint de editar


    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categoriesList = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        int productsCount;
        if (!categoriesList.isEmpty()) {
            for (Category category : categoriesList) {
                productsCount = category.getProducts().size();
                CategoryDTO categoryDTO = new CategoryDTO(
                        category.getId(),
                        category.getTitle(),
                        category.getDescription(),
                        category.getUrlImage(),
                        productsCount
                );
                categoryDTOList.add(categoryDTO);
            }
        }
        return categoryDTOList;
    }
}
