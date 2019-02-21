package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.services.UserService;
import springboot.services.base.PostService;

@Controller
public class Main {

    @Autowired
    @Qualifier("postServiceImpl")
    private PostService postService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    @ModelAttribute("post")
    public PostDTO userRegistrationDto() {
        return new PostDTO();
    }

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("list", postService.listAllPosts());
        return "index";}

    @GetMapping("/adminPage")
    public  String admin(Model model){
        return "admin";}

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }


    @GetMapping("/delete/post/{id}")
    public String deletePost(@PathVariable String id) {
        postService.deleteById(id);

        return "/";
    }




}
