package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.services.base.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Component
@Controller
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postS){
        this.postService = postS;
    }

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
        model.addAttribute("post_id", id);
        return "listApplications";
    }
}
