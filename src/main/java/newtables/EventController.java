package newtables;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
@Controller

public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "index";
    }



    @PostMapping("/")
    public String create(
            @RequestParam String event_title,
            @RequestParam String event_description,
            @RequestParam Long event_id,
            @RequestParam Long user_id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime event_time,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate event_date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate due_Date)
            {

        Event event = new Event();
        event.setEvent_title(event_title);
        event.setEvent_description(event_description);
        event.setEvent_id(event_id);
        event.setUser_id(user_id);
        event.setEvent_date(event_date);
        event.setEvent_time(event_time);
        event.setDue_date(due_Date);
        eventRepository.save(event);
        return "redirect:/";
    }
    
}


 