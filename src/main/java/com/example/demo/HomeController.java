package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.example.demo.model.SigninForm;
import com.example.demo.model.SignupForm;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
public class HomeController implements WebMvcConfigurer{

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/results").setViewName("results");
        }

        @Autowired 
        private UserRepository userRepository;
        

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
    public String categories() {
        return "admin/pages/samples/categories";
    }

    @RequestMapping("/dashboard")
    public String dasshboard() {
        return "admin/pages/index";
    }
    
    @RequestMapping("/add_category")
    public String add_category() {
        return "admin/pages/samples/add_category";
    }
    
    @RequestMapping("/products")
    public String products() {
        return "admin/pages/samples/products";
    }

    @RequestMapping("/add_product")
    public String add_product() {
        return "admin/pages/samples/add_product";
    }
    
    
}
