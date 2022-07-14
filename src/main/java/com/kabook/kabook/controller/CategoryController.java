package com.kabook.kabook.controller;

import com.kabook.kabook.model.Category;
import com.kabook.kabook.model.DTO.CategoryDTO;
import com.kabook.kabook.service.Impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin

public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @PostMapping()
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryServiceImpl.addCategory(category));
    }

    @PutMapping()
    public ResponseEntity<Category> editCategory(@RequestBody Category category){
        ResponseEntity<Category> response = null;
        if(category.getId() != null && categoryServiceImpl.searchById(category.getId()).isPresent())
            response = ResponseEntity.ok(categoryServiceImpl.editCategory(category));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        ResponseEntity<String> response = null;

        if (categoryServiceImpl.searchById(id).isPresent()){
            categoryServiceImpl.deleteCategory(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Category deleted correctly");
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> categoryDTOList = categoryServiceImpl.getAllCategories();
        return categoryDTOList.isEmpty()
                ? ResponseEntity.notFound().build()
                : new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        ResponseEntity<?> response = ResponseEntity.notFound().build();
        Optional<Category> category =  categoryServiceImpl.searchById(id);
        if(category.isPresent()) {
            response = ResponseEntity.ok().body(category.get());
            System.out.println(response);
        }
        return response;
    }
}
