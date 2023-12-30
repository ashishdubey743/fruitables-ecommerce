package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@SpringBootApplication

@Controller
public class HomeController {
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

     public static void main(String[] args) {
        SpringApplication.run(HomeController.class, args);
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
}
