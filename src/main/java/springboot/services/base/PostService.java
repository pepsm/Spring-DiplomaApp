package springboot.services.base;

import org.springframework.data.repository.PagingAndSortingRepository;
import springboot.controllers.dto.PostDTO;
import springboot.models.Post;
import springboot.models.User;

import java.util.List;

public interface PostService extends PagingAndSortingRepository<Post, Integer> {

    List<Post> listAllPosts();
    void deleteById(String id);
    List<Post> findByTopic(String topic);
    List<Post> listByEmployer(String id);
    void update(String id,PostDTO post);
    void closeById(String id);
    Post findById(String id);
    Post save(PostDTO post, User user);
}
