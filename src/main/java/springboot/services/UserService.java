package springboot.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import springboot.models.User;
import springboot.controllers.UserRegistrationDTO;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    User save(UserRegistrationDTO registration);
}