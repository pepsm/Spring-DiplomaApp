package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springboot.models.Candidacy;
import springboot.models.Post;
import springboot.models.User;
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

    @RequestMapping("post/apply/{id}")
    public String applyToPost(@PathVariable String id, Model model){

       Candidacy c = new Candidacy();
       c.setPost_id(postService.findById(id));

       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       if (principal instanceof UserDetails) {
        String username = ((UserDetails)principal).getUsername();
        User u = userService.findByUsername(username);
        c.setUser(u);
       }

        c.setComment("Here");
        candidacyService.save(c);


        model.addAttribute("candidacy", c);

        List<Candidacy> list = candidacyService.listAllCand();
        model.addAttribute("cand_id", list.get(list.size() - 1).getId().toString());

        return "postApply";
    }


    @PostMapping("apply/{cand_id}")
    public String applySave(@PathVariable String cand_id, @ModelAttribute("candidacy") Candidacy candidacy){
            Candidacy c = candidacyService.findById(cand_id);
            c.setComment(candidacy.getComment());
            candidacyService.updateCand(c);
            return "index_user";}

    @ModelAttribute("posts")
    public List<Post> posts() {
        return postService.listAllPosts();
    }




}
