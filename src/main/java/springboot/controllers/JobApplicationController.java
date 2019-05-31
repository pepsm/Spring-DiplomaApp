package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.models.Candidacy;
import springboot.models.Post;
import springboot.models.User;
import springboot.services.base.CandidacyService;
import springboot.services.base.UserService;
import springboot.services.base.PostService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class JobApplicationController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CandidacyService candidacyService;

    // logic of the employee
    @GetMapping("/index_user")
    public String index_user()
    {
        return "index_user";
    }

    @GetMapping("/post/view/{id}")
    public String viewPost(@PathVariable String id, Model model) {

       Post p = postService.findById(id);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = null;
        if (principal instanceof UserDetails) {
            user = userService.findByUsername(((UserDetails)principal).getUsername());
        }

        Candidacy cand = null;
        model.addAttribute("cand_id", -1);
        for(Candidacy c : p.getCandidacyList()){
           if(c.getUser().getUserName().equals(user.getUserName())){
               cand = c;
               cand.setId(c.getId());
               model.addAttribute("cand_id", cand.getId());
               break;
           }
        }

        if (cand == null) {
            cand = new Candidacy();
            cand.setId(null);
            cand.setPost(p);
        }
        model.addAttribute("candidacy", cand);
       return "postApply";
    }

    @PostMapping("apply/{post_id}/{cand_id}")
    public String applySave(@PathVariable("post_id") String post_id, @PathVariable("cand_id") String cand_id, @ModelAttribute("candidacy") Candidacy candidacy){

        Candidacy c = candidacyService.findById(Integer.parseInt(cand_id));
        if(c != null){
            c.setComment(candidacy.getComment());
            candidacyService.updateCand(c);
        }else{
            User user = null;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                user = userService.findByUsername(((UserDetails)principal).getUsername());
            }
            candidacy.setUser(user);
            Post p = postService.findById(post_id);
            candidacy.setPost(p);
            candidacyService.save(candidacy);

        }
            return "index_user";
    }

    // employer
    @GetMapping("reject/{cand_id}")
    public String appReject(@PathVariable("cand_id") String cand_id){

        candidacyService.deleteById(candidacyService.findById(Integer.parseInt(cand_id)));
        return "redirect:/";
    }

    @ModelAttribute("posts")
    public List<Post> posts() {
        return postService.listAllPosts();
    }

}
