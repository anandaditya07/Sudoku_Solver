CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE puzzles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    puzzle TEXT NOT NULL,
    solution TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
