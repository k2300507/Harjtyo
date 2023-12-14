package newtables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import javax.persistence.OneToMany;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Event extends AbstractPersistable<Long>{

    private Long event_id;
    private Long user_id;
    private String event_title;
    private String event_description;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate event_date;
    @DateTimeFormat(pattern = "HH:MM")
    private LocalTime event_time;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate due_date;

    

    @OneToMany 
    @JoinTable(
        name = "event_categories",
        joinColumns = @JoinColumn(name="event_id"),
        inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private List<Category> categories;
}


// -- Events table to store diary entries and calendar events
// CREATE TABLE IF NOT EXISTS events (
//     event_id INT PRIMARY KEY AUTO_INCREMENT,
//     user_id INT,
//     event_title VARCHAR(255) NOT NULL,
//     event_description TEXT,
//     event_date DATE,
//     event_time TIME,
//     due_date DATE,
//     FOREIGN KEY (user_id) REFERENCES users(user_id)
// );
