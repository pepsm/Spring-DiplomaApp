package springboot.services.base;

import springboot.controllers.PostDTO;
import springboot.controllers.UserRegistrationDTO;
import springboot.models.Post;
import springboot.models.User;

import java.util.List;

public interface PostService {

    List<Post> listAllPosts();
    void deleteById(String id);
    Post save(PostDTO post);
}
