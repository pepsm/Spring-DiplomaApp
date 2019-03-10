package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.models.Candidacy;
import springboot.models.Post;
import springboot.models.User;
import springboot.services.CandidacyService;
import springboot.services.UserService;
import springboot.services.base.PostService;

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

    @RequestMapping("/post/view/{id}")
    public String viewPost(@PathVariable String id, Model model) {

       Post p = postService.findById(id);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = null;
        if (principal instanceof UserDetails) {
            user = userService.findByUsername(((UserDetails)principal).getUsername());
        }

        Candidacy cand = null;

        for(Candidacy c : p.getCandidacyList()){
           if(c.getUser().getUserName().equals(user.getUserName())){
               cand = c;
               break;
           }
        }

        if (cand == null) {
            cand = new Candidacy();
            cand.setPost(p);
        }

        model.addAttribute("candidacy", cand);

       return "postApply";
    }


    @PostMapping("apply/{id}")
    public String applySave(@PathVariable("id") String id, @ModelAttribute("candidacy") Candidacy candidacy){

        Integer cId = candidacy.getId();

        if(cId != null){
            Candidacy c = candidacyService.findById(cId.toString());
            c.setComment(candidacy.getComment());
            candidacyService.updateCand(c);
        }else{
            User user = null;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                user = userService.findByUsername(((UserDetails)principal).getUsername());
            }
            candidacy.setUser(user);
            Post p = postService.findById(id);
            candidacy.setPost(p);


            candidacyService.save(candidacy);
        }
            return "index_user";
    }

    @ModelAttribute("posts")
    public List<Post> posts() {
        return postService.listAllPosts();
    }




}
