package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.models.Candidacy;
import springboot.models.Post;
import springboot.models.User;
import springboot.services.CandidacyService;
import springboot.services.UserService;
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
    public String savePost(@ModelAttribute("post") @Valid PostDTO postDTO, BindingResult result){
        if(result.hasErrors()){
            return "posts";
        }
        postService.save(postDTO);
        return "posts";
    }

    @GetMapping("post/close/{id}")
    public  String closePost(@PathVariable String id, HttpServletRequest request){
        postService.closeById(id);
        return "index";
    }

    @GetMapping("post/update")
    public  String updatePostPage(){
        return "updatePost";
    }

    @GetMapping("list/{id}")
    public String listCandidacy(@PathVariable String id, Model model){


        model.addAttribute("cands", candidacyService.listApplicants(id));

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
