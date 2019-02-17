package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.services.base.PostService;

import javax.validation.Valid;

@Component
@Controller
@RequestMapping("/posts")
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

    @GetMapping
    public String showCreatePostForm() {
        return "posts";
    }

    @PostMapping
    public String savePost(@ModelAttribute("post") @Valid PostDTO postDTO, BindingResult result){
        if(result.hasErrors()){
            return "posts";
        }
        postService.save(postDTO);
        return "redirect:/posts?success";
    }
}
