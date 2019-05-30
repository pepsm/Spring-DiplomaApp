package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.controllers.dto.PostDTO;
import springboot.models.Post;
import springboot.models.User;
import springboot.services.base.UserService;
import springboot.services.base.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @ModelAttribute("post")
    public PostDTO userRegistrationDto() {
        return new PostDTO();
    }

    @GetMapping("/")
    public String root(Model model, Authentication authentication) {
        model.addAttribute("list",
                userService.listPostsOfUser(
                        findCurrentUser(authentication).getUsername()
                ));
        return "index";}



    @GetMapping("/test")
    public String test(HttpServletRequest request)
    {
        int page = 0; //default page number is 0 (yes it is weird)
        int size = 10; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        
        return "test";
}




    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "index";
    }


    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable String id, HttpServletRequest request) {
        postService.deleteById(id);
        return "superuser";
    }

    @GetMapping("/userSettings")
    public String userSettings(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            model.addAttribute("user", userService.findByUsername(((UserDetails)principal).getUsername()));
        }else {
            return "redirect:/";
        }

        return "userSettings";
    }

    @PostMapping("/update/user")
    public String updateUser(@PathParam("user") User user){
        User u = userService.findByUsername(user.getUserName());
        userService.update(u.getId().toString(), user);

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


    public User findCurrentUser(Authentication authentication){
        return userService.findByUsername(authentication.getName());
    }
}
