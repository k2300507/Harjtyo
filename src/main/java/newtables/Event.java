package newtables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Event extends AbstractPersistable<Long>{
    private Integer event_id;
    private Integer user_id;
    private String event_title;
    private String event_description;
    private Date event_date;
    private Date event_time;
    private Date due_date;

    

    @ManyToMany 
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
