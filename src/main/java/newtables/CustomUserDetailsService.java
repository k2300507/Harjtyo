package newtables;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserWithEventsAndCategories(username);
       
        if (user == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }



        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode(user.getPassword_Hash()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword_Hash(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }

    public void saveUser(User user) {
        // Koodaa salasana ennen tallennusta
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword_Hash(passwordEncoder.encode(user.getPassword_Hash()));
        userRepository.save(user);

        System.out.println(passwordEncoder.encode(user.getPassword_Hash()));
    }
}
    

