package newtables;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired 
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private UserService userService;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    
    

    @GetMapping("/event/{id}")
    public String getOne(Model model, @PathVariable Long id) {
        model.addAttribute("event", eventRepository.getOne(id));
        return "event";
    }

    @GetMapping("/input/")
    public String input(Model model, Principal principal ) {
        // onko käyttäjä kirjautunut sisään?
        if (principal == null) {
            // Käyttäjä ei ole kirjautunut, ohjaa kirjautumissivulle
            return "redirect:/login";
        }
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
         return "input";
    }


    @PostMapping("/input/")
    public String create(
            @RequestParam String event_title,
            @RequestParam String event_description,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime event_time,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate event_date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate due_date,
            @RequestParam Long category_id,
            Principal principal)
            {

        Event event = new Event();
        event.setEvent_title(event_title);
        event.setEvent_description(event_description);
        event.setEvent_date(event_date);
        event.setEvent_time(event_time);
        event.setDue_date(due_date);

        // Hakee kirjautuneen käyttäjän 
        User loggedInUser = userService.getUserWithEventsAndCategories(principal.getName());
        event.setUser(loggedInUser);

        // Tarkista onko event.getCategories() tyhjä, ja alusta tarpeen vaatiessa
        if (event.getCategories() == null) {
            event.setCategories(new ArrayList<>());
        }


        Category selectedCategory = categoryRepository.findById(category_id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid category id"));
        event.getCategories().add(selectedCategory);
        
        eventRepository.save(event);
        return "redirect:/";
    }


    @GetMapping("/event/delete/{id}")
    public String showDeleteForm(Model model, @PathVariable Long id, Principal principal) {
        // onko käyttäjä kirjautunut sisään?
        if (principal == null) {
            // Käyttäjä ei ole kirjautunut, ohjaa kirjautumissivulle
            return "redirect:/login";
        }

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID"));
    
        model.addAttribute("event", event);
        return "delete-event";
    }
    
    @PostMapping("/event/delete/{id}")
    public String delete(@PathVariable Long id) {
        eventRepository.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/event/update/{id}")
    public String showUpdateForm(Model model, @PathVariable Long id, Principal principal) {
        // onko käyttäjä kirjautunut sisään?
        if (principal == null) {
            // Käyttäjä ei ole kirjautunut, ohjaa kirjautumissivulle
            return "redirect:/login";
        }


        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID"));
    
        model.addAttribute("event", event);
        model.addAttribute("allCategories", categoryRepository.findAll());
        return "update";
    }
    
    @PostMapping("/event/update/{id}")
    public String update(@ModelAttribute Event updatedEvent,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime x_event_time,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate x_event_date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate x_due_date,
            @RequestParam Long category_id,
            Principal principal) {
        System.err.println("virhe" + x_event_date);
        updatedEvent.setEvent_date(x_event_date);
        updatedEvent.setEvent_time(x_event_time);
        updatedEvent.setDue_date(x_due_date);

                // Tarkista onko event.getCategories() tyhjä, ja alusta tarpeen vaatiessa
        if (updatedEvent.getCategories() == null) {
            updatedEvent.setCategories(new ArrayList<>());
        }


        Category selectedCategory = categoryRepository.findById(category_id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid category id"));
       updatedEvent.getCategories().add(selectedCategory);

        // Hakee kirjautuneen käyttäjän
        User loggedInUser = userService.getUserWithEventsAndCategories(principal.getName());
        updatedEvent.setUser(loggedInUser);
        
        eventRepository.save(updatedEvent);
        return "redirect:/";
    }



   

}


 