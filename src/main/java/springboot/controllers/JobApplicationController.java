package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springboot.models.Candidacy;
import springboot.models.Post;
import springboot.services.CandidacyService;
import springboot.services.UserService;
import springboot.services.base.PostService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
public class JobApplicationController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CandidacyService candidacyService;

    @RequestMapping( value = "/index_user", method = GET )
    public String index_user(){
        return "index_user";
    }

    @PostMapping("post/apply/{id}")
    public String applyToPost(@PathVariable String id){
       Candidacy c = new Candidacy();
         //c.setPost_id(postService.findById(id));
        //c.setUser(userService.findByUsername(currentUser.getUsername()));

        return "postApply";
    }


    @PostMapping("apply")
    public String applySave(@ModelAttribute("candidacy") @Valid CandidacyDTO candidacyDTO){

        candidacyService.save(candidacyDTO);
        return "index_user";}

    @ModelAttribute("posts")
    public List<Post> posts() {
        return postService.listAllPosts();
    }




}
