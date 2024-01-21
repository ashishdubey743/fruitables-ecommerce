package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import com.example.demo.model.entity.migrations.Category;
import com.example.demo.model.entity.migrations.Product;
import com.example.demo.model.entity.migrations.User;
import com.example.demo.model.forms.AddCategoryForm;
import com.example.demo.model.forms.AddProductForm;
import com.example.demo.model.forms.SigninForm;
import com.example.demo.model.forms.SignupForm;
import com.example.demo.model.repository.CategoryRepository;
import com.example.demo.model.repository.ProductRepository;
import com.example.demo.model.repository.UserRepository;

import java.util.Optional;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import org.springframework.ui.Model;

@Controller
public class HomeController implements WebMvcConfigurer{

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/results").setViewName("results");
        }

        @Autowired 
        private UserRepository userRepository;
        @Autowired
        private CategoryRepository categoryRepository;
        @Autowired
        private ProductRepository productRepository;
        // @Autowired
        // private ImageRepository imageRepository;

        @Value("${upload.folder}")
        private String uploadFolder;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }
   
    @RequestMapping("/shop")
    public String shop() {
        return "shop";
    }

    @RequestMapping("/shop-detail")
    public String shop_detail() {
        return "shop-detail";
    }

    @RequestMapping("/contact")
    public String cuntact() {
        return "contact";
    }

    @RequestMapping("/user_cart")
    public String cart() {
        return "user_cart";
    }

    @RequestMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @RequestMapping("/testimonial")
    public String testimonial() {
        return "testimonial";
    }

    @RequestMapping("/404")
    public String pg_404() {
        return "404";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("signinForm", new SigninForm());
        return "admin/pages/samples/login";
    }
    
    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "admin/pages/samples/register";
    }

    @PostMapping("/processFormSignin")
    public String submitForm(@Valid SigninForm signinForm, BindingResult bindingResult, HttpSession session) {

		if (bindingResult.hasErrors()) {
			return "admin/pages/samples/login";
		}
        if(userRepository.existsByEmail(signinForm.getEmail())){
            User user = userRepository.findByEmail(signinForm.getEmail());
            if("admin@gmail.com".equals(user.getEmail()) && "admin".equals(user.getPassword())){
                session.setAttribute("user_id", user.getId());
                session.setAttribute("user", user);
                return "admin/pages/index";
            }
            session.setAttribute("user_id", user.getId());
            session.setAttribute("user", user);
            return "index";
        }
        bindingResult.rejectValue("email", "error.email", "No account is registered with this email.");
		return "admin/pages/samples/login";
	}

    @PostMapping("/processFormSignup")
    public String submitForm(@ModelAttribute @Valid SignupForm signupForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "admin/pages/samples/register";
        }
    
        if (userRepository.existsByEmail(signupForm.getEmail())) {
            bindingResult.rejectValue("Email", "error.Email", "Email already exists");
            return "admin/pages/samples/register";
        }
    
        User user = new User();
        user.setUsername(signupForm.getUsername());
        user.setEmail(signupForm.getEmail());
        user.setCountry(signupForm.getCountry());
        user.setPassword(signupForm.getPassword());
        user.setTerms(signupForm.getTerms());
        userRepository.save(user);
        session.setAttribute("user_id", user.getId());
        session.setAttribute("user", user);
        return "admin/pages/samples/thank_you";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
    @RequestMapping("/categories")
    public String categories(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        for( Category category: categories){
            long productCount = productRepository.countByCategory(category.getName());
            category.setProductCount(productCount);
        }
        
        return "admin/pages/samples/categories";
    }

    @RequestMapping("/dashboard")
    public String dasshboard() {
        return "admin/pages/index";
    }
    
    @RequestMapping(value = {"/add_category", "/add_category/{categoryId}"})
    public String addOrEditCategory(@PathVariable(required = false) Integer categoryId, Model model) {
        if (categoryId != null) {
            model.addAttribute("editCategoryValue", 1);
            // Editing an existing category
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            
            if (optionalCategory.isPresent()) {
                Category existingCategory = optionalCategory.get();
                AddCategoryForm editCategoryForm = new AddCategoryForm();
                editCategoryForm.setCategoryId(existingCategory.getId());
                editCategoryForm.setCategoryName(existingCategory.getName());
                editCategoryForm.setCategoryDescription(existingCategory.getDescription());
                model.addAttribute("categoryId", categoryId);
                model.addAttribute("addCategory", editCategoryForm);
            } else {
                // Handle case where category with the given ID is not found
                return "redirect:/error";
            }
    } else {
        // Adding a new category
        model.addAttribute("addCategory", new AddCategoryForm());
        model.addAttribute("addCategoryValue", 1);
    }

    return "admin/pages/samples/add_category";
}

    
    
    @RequestMapping("/products")
    public String products(Model model) {
        List<Product> products = productRepository.findAll();
        
        model.addAttribute("products", products);
        return "admin/pages/samples/products";
    }

    @RequestMapping(value = {"/add_product", "/add_product/{id}"})
    public String add_edit_product(@PathVariable(required = false) Integer id, Model model) {
        System.out.println(id);
        if(id != null){
            model.addAttribute("editProductValue", 1);
            // code for editing product
            Optional<Product> optionalProduct = productRepository.findById(id);
            if(optionalProduct.isPresent()){
                System.out.println("present");
                Product existingProduct = optionalProduct.get();
                AddProductForm ediProductForm = new AddProductForm();
                List<Category> categories = categoryRepository.findAll();
                ediProductForm.setName(existingProduct.getProductName());
                ediProductForm.setQuantity(existingProduct.getQuantity());
                ediProductForm.setCategory(existingProduct.getCategory());
                ediProductForm.setSku(existingProduct.getSku());
                model.addAttribute("id", id);
                model.addAttribute("test", 1);
                model.addAttribute("addProductForm", ediProductForm);
                model.addAttribute("categories", categories);
            }
        }else{
            model.addAttribute("addProductForm", new AddProductForm());
            List<Category> categories = categoryRepository.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("addProductValue", 1);
        }
        return "admin/pages/samples/add_product";
    }
    
    @PostMapping("/processAddCategory")
    public String processAddCategory(@ModelAttribute("addCategory") @Valid AddCategoryForm addCategory, BindingResult bindingResult, Model model) {
        // Process of editing Category
        if(addCategory.getCategoryId() != null){
            Optional<Category> optionalCategory = categoryRepository.findById(addCategory.getCategoryId());
            if (categoryRepository.existsByNameAndIdNot(addCategory.getCategoryName(), addCategory.getCategoryId())) {
                bindingResult.rejectValue("categoryName", "error.categoryName", "Category already exists");
                model.addAttribute("categoryId", addCategory.getCategoryId());
                model.addAttribute("editCategoryValue", 1);
                return "admin/pages/samples/add_category";
            }
            if (optionalCategory.isPresent()) {
                Category existingCategory = optionalCategory.get();
                existingCategory.setName(addCategory.getCategoryName());
                existingCategory.setDescription(addCategory.getCategoryDescription());
                categoryRepository.save(existingCategory);
                return "redirect:/categories";
            }
        }
        // Add new category
        if(bindingResult.hasErrors()){
            return "admin/pages/samples/add_category";
        }
        if (categoryRepository.existsByName(addCategory.getCategoryName())) {
            bindingResult.rejectValue("categoryName", "error.categoryName", "Category already exists");
            return "admin/pages/samples/add_category";
        }
        Category category = new Category();
        category.setName(addCategory.getCategoryName());
        category.setDescription(addCategory.getCategoryDescription());
        categoryRepository.save(category);
        return "redirect:/categories";
    }
    
    // Products
    @PostMapping("/processAddProduct")
    public String processAddProduct( @RequestParam("image") MultipartFile file, @ModelAttribute("addProductForm") @Valid AddProductForm addProductForm, BindingResult bindingResult,
        HttpServletRequest request, Model model) throws IOException, SerialException, SQLException {
            if(addProductForm.getId() != null){
                System.out.println("Test");
                Optional<Product> optionalProduct = productRepository.findById(addProductForm.getId());
                if (productRepository.existsByProductNameAndIdNot(addProductForm.getName(), addProductForm.getId())) {
                    bindingResult.rejectValue("categoryName", "error.categoryName", "Product already exists");
                    model.addAttribute("id", addProductForm.getId());
                    model.addAttribute("editProductValue", 1);
                    return "admin/pages/samples/add_category";
                }
                if (optionalProduct.isPresent()) {
                    Product existingProduct = optionalProduct.get();
                    existingProduct.setProductName(addProductForm.getName());
                    existingProduct.setQuantity(addProductForm.getQuantity());
                    existingProduct.setSku(addProductForm.getSku());
                    existingProduct.setCategory(addProductForm.getCategory());
                    if(!addProductForm.getImage().isEmpty()){
                        String fileName = existingProduct.getImage().replaceAll(".*/", "");
                        Path filePath = Paths.get(uploadFolder + fileName);
                        if (Files.exists(filePath)) {
                            Files.delete(filePath);
                        }
                        byte[] bytes = file.getBytes();
                        String Path = uploadFolder + file.getOriginalFilename();
                        Path path = Paths.get(Path);

                        Files.createDirectories(path.getParent());
                        Files.write(path, bytes);
                        existingProduct.setImage(path.toString().substring(path.toString().indexOf("admin/")));
                    }
                    productRepository.save(existingProduct);
                    return "redirect:/products";
                }
            }
            if (bindingResult.hasErrors()) {
                return "admin/pages/samples/add_product";
            }
        
            if (productRepository.existsByProductName(addProductForm.getName())) {
                bindingResult.rejectValue("name", "error.name", "Product already exists");
                List<Category> categories = categoryRepository.findAll();
                model.addAttribute("categories", categories);
                return "admin/pages/samples/add_product";
            }
            if(productRepository.existsBySku(addProductForm.getSku())){
                bindingResult.rejectValue("sku", "error.sku", "SKU already exists");
                List<Category> categories = categoryRepository.findAll();
                model.addAttribute("categories", categories);
                return "admin/pages/samples/add_product";
            }

        Product product  = new Product();
        product.setProductName(addProductForm.getName());
        product.setQuantity(addProductForm.getQuantity());
        product.setSku(addProductForm.getSku());
        product.setCategory(addProductForm.getCategory());

        byte[] bytes = file.getBytes();
        String filePath = uploadFolder + file.getOriginalFilename();
        Path path = Paths.get(filePath);

        Files.createDirectories(path.getParent());
        Files.write(path, bytes);
        product.setImage(path.toString().substring(path.toString().indexOf("admin/")));
        productRepository.save(product);
        return "redirect:/products";
    }
}
