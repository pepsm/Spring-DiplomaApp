package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.controllers.PostDTO;
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
    public List<Post> listByEmployer(String id) {
         return null;
    }

    @Override
    public void update(String id, PostDTO post) {
     Optional<Post> opt = postRepository.findById(Long.parseLong(id));
     if(opt.isPresent())
     {
         Post p = opt.get();
         p.setText(post.getText());
         p.setTopic(post.getTopic());
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
        p.setText(post.getText());
        p.setActive(true);
        return  postRepository.save(p);
    }

}
