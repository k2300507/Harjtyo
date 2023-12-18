package newtables;
import java.time.LocalDate;
import java.time.LocalTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "index";
    }
    

    @GetMapping("/event/{id}")
    public String getOne(Model model, @PathVariable Long id) {
        model.addAttribute("event", eventRepository.getOne(id));
        return "event";
    }



    @PostMapping("/")
    public String create(
            @RequestParam String event_title,
            @RequestParam String event_description,
            // @RequestParam Long event_id,
            // @RequestParam Long user_id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime event_time,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate event_date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate due_Date)
            {

        Event event = new Event();
        event.setEvent_title(event_title);
        event.setEvent_description(event_description);
        // event.setEvent_id(event_id);
        // event.setUser_id(user_id);
        event.setEvent_date(event_date);
        event.setEvent_time(event_time);
        event.setDue_date(due_Date);
        eventRepository.save(event);
        return "redirect:/";
    }
    
}


 