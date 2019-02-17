package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.services.base.PostService;

@Controller
public class Main {

    private PostService postService;

    @Autowired
    public Main(PostService postS){
        this.postService = postS;
    }

    @ModelAttribute("post")
    public PostDTO userRegistrationDto() {
        return new PostDTO();
    }

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("list", postService.listAllPosts());
        return "index";}

    @GetMapping("/admin")
    public  String admin(){return "adminPage";}

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

}
