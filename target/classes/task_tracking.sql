-- Users table to store user information
CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    -- Add other user-related fields as needed
    UNIQUE KEY unique_username (username)
);

-- Categories table to store different event categories
CREATE TABLE IF NOT EXISTS categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    category_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    UNIQUE KEY unique_category (user_id, category_name)
);

-- Events table to store diary entries and calendar events
CREATE TABLE IF NOT EXISTS events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    event_title VARCHAR(255) NOT NULL,
    event_description TEXT,
    event_date DATE,
    event_time TIME,
    due_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Junction table to represent the many-to-many relationship between events and categories
CREATE TABLE IF NOT EXISTS event_categories (
    event_id INT,
    category_id INT,
    PRIMARY KEY (event_id, category_id),
    FOREIGN KEY (event_id) REFERENCES events(event_id),
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

-- Sample query to get events for a specific category
-- SELECT events.* FROM events
-- INNER JOIN event_categories ON events.event_id = event_categories.event_id
-- WHERE event_categories.category_id = [category_id];

-- Sample query to get categories for a specific event
-- SELECT categories.* FROM categories
-- INNER JOIN event_categories ON categories.category_id = event_categories.category_id
-- WHERE event_categories.event_id = [event_id];