package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.controllers.PostDTO;
import springboot.models.JobType;
import springboot.models.Post;
import springboot.repositories.PostRepository;
import springboot.repositories.UserRepository;
import springboot.services.base.PostService;
import sun.reflect.generics.repository.GenericDeclRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;


    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public PostServiceImpl(){}

    private List<Post> list;

    @Override
    public List<Post> listAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        postRepository.deleteById(Long.parseLong(id));
    }


    @Override
    public Post findById(String id) {

        List<Post> list = postRepository.findAll();

        for (Post p : list){
            if(p.getId() == Long.parseLong(id))
            {
                return p;
            }
        }

        return null;

    }

    @Override
    public List<Post> listByEmployer(String id) {
         return null;
    }

    @Override
    public void update(String id, PostDTO post) {
     Optional<Post> opt = postRepository.findById(Long.parseLong(id));
     if(opt.isPresent())
     {
         Post p = opt.get();
         p.setDescription(post.getDescription());
         p.setTopic(post.getTopic());
         p.setLocation(post.getLocation());
         p.setJobType(post.getJobType());
     }
    }

    @Override
    public void closeById(String id) {
       Optional<Post> opt = postRepository.findById(Long.parseLong(id));
       opt.ifPresent(post -> post.setActive(false));
       postRepository.save(opt.get());
    }

    @Override
    public Post save(PostDTO post) {
        Post p = new Post();
        p.setTopic(post.getTopic());
        p.setDescription(post.getDescription());
        p.setJobType(post.getJobType());
        p.setLocation(post.getLocation());
        p.setActive(true);
        return  postRepository.save(p);
    }

}
