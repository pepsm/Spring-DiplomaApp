package springboot.services;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.models.Post;
import springboot.models.Role;
import springboot.models.User;
import springboot.repositories.UserRepository;
import springboot.controllers.dto.UserRegistrationDTO;
import springboot.services.base.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByUsername(String username){
        for (User u : userRepository.findAll()) {
            if(u.getUserName().equals(username)|| u.getEmail().equals(username))return  u;
        }
        return null;
    }

    public List<User> findByUserNameOrEmail(String username) {

        List<User> result = userRepository.findAll().stream()
                .filter(x -> x.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());

        return result;

    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Post> listPostsOfUser(String username) {
         return findByUsername(username).getPostList();
    }

    public User save(UserRegistrationDTO registration){
        User user = new User();
        user.setUserName(registration.getUserName());
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Role(registration.getRole())));
        return userRepository.save(user);
    }



    @Override
    public void update(String id, User user) {
        Optional<User> opt = userRepository.findById(Long.parseLong(id));
        if(opt.isPresent())
        {
            User u = opt.get();
            u.setUserName(user.getUserName());
            u.setEmail(user.getEmail());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(u);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}