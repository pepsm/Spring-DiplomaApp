package springboot.services.base;

import org.springframework.security.core.userdetails.UserDetailsService;
import springboot.models.Post;
import springboot.models.User;
import springboot.controllers.dto.UserRegistrationDTO;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    List<User> findByUserNameOrEmail(String username);

    List<User> listAllUsers();

    List<Post> listPostsOfUser(String username);

    void update(String id, User user);

    User save(UserRegistrationDTO registration);
}