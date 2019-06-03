package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.models.DTO.PostDTO;
import springboot.models.Candidacy;
import springboot.models.Post;
import springboot.models.User;
import springboot.services.base.CandidacyService;
import springboot.services.base.UserService;
import springboot.services.base.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Component
@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CandidacyService candidacyService;

    @ModelAttribute("post")
    public PostDTO userRegistrationDto() {
        return new PostDTO();
    }

    @GetMapping("/posts")
    public String showCreatePostForm() {
        return "posts";
    }

    @PostMapping("/posts")
    public String savePost(@ModelAttribute("post") @Valid PostDTO postDTO, BindingResult result, Authentication authentication){
        if(result.hasErrors()){
            return "posts";
        }
        User user = userService.findByUsername(authentication.getName());
        postService.save(postDTO, user);
        return "redirect:/";
    }

    @GetMapping("post/close/{id}")
    public  String closePost(@PathVariable String id, HttpServletRequest request){
        postService.closeById(id);
        return "redirect:/";
    }

    @GetMapping("edit/{id}")
    public String editPost(@PathVariable String id, Model model)
    {
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("id", id);
        return "editPost";
    }

    @PostMapping("edit/update/{id}")
    public  String updatePost(@ModelAttribute("post") @Valid PostDTO postDTO, BindingResult result, @PathVariable String id){
        if(result.hasErrors()){
            return "editPost";
        }
        postService.update(id, postDTO);
        return "redirect:/";
    }

    @GetMapping("list/{id}")
    public String listCandidacy(@PathVariable String id, Model model){
        model.addAttribute("cands", postService.findById(id).getCandidacyList());
        return "listApplications";
    }

    @ModelAttribute("users")
    public List<User> users() {
        return userService.listAllUsers();
    }


    @ModelAttribute("posts")
    public List<Post> posts() {
        return postService.listAllPosts();
    }

    @ModelAttribute("applicants")
    public  List<Candidacy> applicants(){return candidacyService.listAllCand();}
}
