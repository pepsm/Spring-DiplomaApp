package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springboot.models.Message;
import springboot.models.Post;
import springboot.models.User;
import springboot.repositories.MessageRepository;
import springboot.services.MessageService;
import springboot.services.UserService;
import springboot.services.base.PostService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class Main {

    @Autowired
//    @Qualifier("postServiceImpl")
    private PostService postService;

    @Autowired
//    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    private MessageService messageService;

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
        model.addAttribute("posts", postService.listAllPosts());
        return "admin";}

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }


    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable String id, HttpServletRequest request) {
        postService.deleteById(id);
        return "admin";
    }

    @RequestMapping( value = "/messages", method = POST )
    public String MessageSubmit(@ModelAttribute("messages") @Validated MessageDTO messageDTO)
    {
        messageService.save(messageDTO);
        return "redirect:/";
    }

    @ModelAttribute("users")
    public List<User> users() {
        return userService.listAllUsers();
    }

    @ModelAttribute("posts")
    public List<Post> posts() {
        return postService.listAllPosts();
    }

    @ModelAttribute("messages")
    public List<Message> messages() {
        return messageService.listAllMessages();
    }


}
