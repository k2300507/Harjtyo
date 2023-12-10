package newtables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import java.util.List;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class User extends AbstractPersistable<Long> {
    private String userId;
    private String username;
    private String passwordHash;

    @OneToMany
    @JoinTable(
        name = "categories",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id") 
    )
    private List<Category> categories;

    @OneToMany
    @JoinTable(
        name = "events",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id") 
    )
    private List<Category> events;

}


