package newtables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public User getUserWithEventsAndCategories(String username) {
        User user = userRepository.findByUsername(username);
        
        // Tarkista, että käyttäjä löytyi
        if (user != null) {
            // Tämä laukaisee "lazy loadingin" ja hakee events-kokoelman
            user.getEvents().size();
            // Tämä laukaisee "lazy loadingin" ja hakee categories-kokoelman
            user.getCategories().size();
        }
        
        return user;
    }
}
