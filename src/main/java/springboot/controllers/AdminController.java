package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springboot.services.base.UserService;
import springboot.services.base.PostService;

@Controller
public class AdminController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/adminPage")
    public  String admin(){
        return "admin";
    }

    @GetMapping("/listUsers")
    public String adminList(){return "listUsers";}

}
