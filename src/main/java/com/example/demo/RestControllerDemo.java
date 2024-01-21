package com.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.migrations.Category;
import com.example.demo.model.entity.migrations.Product;
import com.example.demo.model.entity.migrations.User;
import com.example.demo.model.repository.CategoryRepository;
import com.example.demo.model.repository.ProductRepository;
import com.example.demo.model.repository.UserRepository;

import java.util.Optional;

@RestController
public class RestControllerDemo {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Value("${upload.folder}")
    private String uploadFolder;

    @PostMapping("/update_category_status")
    public Map<String, Object> updateCategoryStatus(@RequestBody Category category) {
        Map<String, Object> response = new HashMap<>();
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setStatus(existingCategory.getStatus() == '0' ? '1' : '0');
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

    @PostMapping("/delete_category")
    public Map<String, Object> deleteCategory(@RequestBody Category category) throws IOException {
        Map<String, Object> response = new HashMap<>();
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            List<Product> productList = productRepository.findByCategory(existingCategory.getName());
            if(existingCategory != null){
                    categoryRepository.delete(existingCategory);
                    if (!productList.isEmpty()) {
                        for(Product product: productList){
                            String fileName = product.getImage().replaceAll(".*/", "");
                            Path filePath = Paths.get(uploadFolder + fileName);
                            if (Files.exists(filePath)) {
                                Files.delete(filePath);
                            }
                        }
                        productRepository.deleteAll(productList);
                    }
                    response.put("status", 200);
                    response.put("msg", "Category Deleted");
                }
        }
        return response;
    }
    

    @PostMapping("/delete_product")
    public Map<String, Object> deleteProduct(@RequestBody Product product) throws IOException {
        Map<String, Object> response = new HashMap<>();
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            if(existingProduct != null){
                    productRepository.delete(existingProduct);

                    
                    String fileName = existingProduct.getImage().replaceAll(".*/", "");
                    Path filePath = Paths.get(uploadFolder + fileName);
                    if (Files.exists(filePath)) {
                        Files.delete(filePath);
                    }
                    response.put("status", 200);
                    response.put("msg", "Product Deleted");
                }else {
                    response.put("status", 404);
                    response.put("msg", "Product not found");
                }
        }
        return response;
    }

    @PostMapping("/delete_user")
    public Map<String, Object> delete_user(@RequestBody User user) throws IOException {
        Map<String, Object> response = new HashMap<>();
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if(existingUser != null){
                    userRepository.delete(existingUser);

                    
                    // String fileName = existingUser.getImage().replaceAll(".*/", "");
                    // Path filePath = Paths.get(uploadFolder + fileName);
                    // if (Files.exists(filePath)) {
                    //     Files.delete(filePath);
                    // }
                    response.put("status", 200);
                    response.put("msg", "User Deleted");
                }else {
                    response.put("status", 404);
                    response.put("msg", "User not found");
                }
        }
        return response;
    }    
}
