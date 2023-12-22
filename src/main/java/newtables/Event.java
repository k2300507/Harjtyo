package newtables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@NoArgsConstructor
@AllArgsConstructor


@Table(name = "Event")
@ToString
public class Event implements Serializable{


  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    private Long event_id;

        
    @Column(name = "event_title")
    private String event_title;
    @Column(name = "event_description")
    private String event_description;

    @Column(name = "event_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate event_date;

    @Column(name = "event_time")
    @DateTimeFormat(pattern = "HH:MM")
    private LocalTime event_time;

    @Column(name = "due_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate due_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
        name = "event_categories",
        joinColumns = @JoinColumn(name="event_id"),
        inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private List<Category> categories;
}

