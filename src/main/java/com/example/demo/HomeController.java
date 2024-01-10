package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import jakarta.validation.Valid;
import com.example.demo.model.SigninForm;
import org.springframework.ui.Model;



@Controller
public class HomeController implements WebMvcConfigurer{

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/results").setViewName("results");
        }

        @Autowired 
        private UserRepository userRepository;
        @PostMapping(path="/add")
        public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
            User n = new User();
            n.setName(name);
            n.setEmail(email);
            userRepository.save(n);
            return "Saved";
    }

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
    public String register() {
        return "admin/pages/samples/register";
    }

    @PostMapping("/processFormSignin")
    public String submitForm(@Valid SigninForm signinForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "admin/pages/samples/login";
		}
		return "index";
	}
    
}
