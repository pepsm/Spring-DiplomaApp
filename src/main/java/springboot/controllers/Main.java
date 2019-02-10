package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.models.FileModel;
import springboot.repositories.FileRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Main {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/admin")
    public  String admin(){return "admin";}

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

}
