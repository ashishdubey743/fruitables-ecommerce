package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.migrations.Category;
import com.example.demo.model.repository.CategoryRepository;
import java.util.Optional;

@RestController
public class RestControllerDemo {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/update_category_status")
public Map<String, Object> updateCategoryStatus(@RequestBody Category category) {
    Map<String, Object> response = new HashMap<>();
    Optional<Category> optionalCategory = categoryRepository.findById(category.getId());

    if (optionalCategory.isPresent()) {
        Category existingCategory = optionalCategory.get();

        // Toggle the status
        existingCategory.setStatus(existingCategory.getStatus() == '0' ? '1' : '0');

        // Save the updated category to the database
        categoryRepository.save(existingCategory);

        response.put("status", 200);
        response.put("msg", existingCategory.getStatus() == '1' ? "Category activated" : "Category deactivated");
        response.put("category", existingCategory);
    } else {
        response.put("status", 404);
        response.put("msg", "Category not found");
    }

    return response;
}

}
