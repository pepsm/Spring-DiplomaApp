package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.controllers.PostDTO;
import springboot.models.Post;
import springboot.repositories.PostRepository;
import springboot.services.base.PostService;
import sun.reflect.generics.repository.GenericDeclRepository;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    private List<Post> list;

    @Override
    public List<Post> listAllPosts() {
        return list = postRepository.findAll();
    }

    @Override
    public Post save(PostDTO post) {
        Post p = new Post();
        p.setTopic(post.getTopic());
        p.setText(post.getText());
        return  postRepository.save(p);
    }
}