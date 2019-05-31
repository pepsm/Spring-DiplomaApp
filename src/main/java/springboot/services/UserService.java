package springboot.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import springboot.models.User;
import springboot.controllers.UserRegistrationDTO;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    List<User> listAllUsers();

    void update(String id, User user);

    User save(UserRegistrationDTO registration);
}