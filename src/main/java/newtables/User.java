package newtables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


import java.util.List;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class User extends AbstractPersistable<Long> {
    private Integer userId;
    private String username;
    private String password_Hash;



}

// -- Users table to store user information
// CREATE TABLE IF NOT EXISTS users (
//     user_id INT PRIMARY KEY AUTO_INCREMENT,
//     username VARCHAR(255) NOT NULL,
//     password_hash VARCHAR(255) NOT NULL,
//     -- Add other user-related fields as needed
//     UNIQUE KEY unique_username (username)
// );
