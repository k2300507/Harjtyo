package newtables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

import javax.persistence.ManyToMany;


import java.util.List;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Category extends AbstractPersistable<Long>{
    private Integer category_id;
    private Integer user_id;
    private String category_name;


    @ManyToMany (mappedBy="categories")
    private List<User> users;

    @ManyToMany (mappedBy="categories")
    private List<Event> events;
    


}

// -- Categories table to store different event categories
// CREATE TABLE IF NOT EXISTS categories (
//     category_id INT PRIMARY KEY AUTO_INCREMENT,
//     user_id INT,
//     category_name VARCHAR(255) NOT NULL,
//     FOREIGN KEY (user_id) REFERENCES users(user_id),
//     UNIQUE KEY unique_category (user_id, category_name)
// );
