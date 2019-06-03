package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public String root(Model model, Authentication authentication){

        PageRequest pageable = PageRequest.of( 0, 3);
        Page<Post> postPage = postService.getPaginatedPostsofUser(userService.findByUsername(authentication.getName()),pageable);

        int totalPages = postPage.getTotalPages();

        if(totalPages >= 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("activePostList", true);
        model.addAttribute("postList", postPage.getContent());
        return "index";
    }

    @GetMapping("/page/{page}")
    public String listArticlesPageByPage(@PathVariable("page") int page, Model model, Authentication authentication) {

        PageRequest pageable = PageRequest.of( page - 1, 3);

        Page<Post> postPage = postService.getPaginatedPostsofUser(userService.findByUsername(authentication.getName()),pageable);

        int totalPages = postPage.getTotalPages();
        if(totalPages >= 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("activePostList", true);
        model.addAttribute("postList", postPage.getContent());
        return "index";
    }

    @GetMapping("list/all")
    public String listAll(Model model, Authentication authentication){

        PageRequest pageable = PageRequest.of( 0, 12);
        Page<Post> postPage = postService.getPaginatedPosts(pageable);

        int totalPages = postPage.getTotalPages();
        if(totalPages >= 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("activePostList", true);
        model.addAttribute("postList", postPage.getContent());

        return "listall";
    }

    @GetMapping("list/page/{page}")
    public String getAllByPages(@PathVariable("page") int page, Model model, Authentication authentication){
        PageRequest pageable = PageRequest.of(page - 1, 12);

        Page<Post> postPage = postService.getPaginatedPosts(pageable);
        int totalPages = postPage.getTotalPages();
        if (totalPages >= 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("activePostList", true);
        model.addAttribute("postList", postPage.getContent());
        return "listall";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "index_test";
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
