CREATE DATABASE library_db;

USE library_db;

CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    isAvailable BOOLEAN DEFAULT TRUE
);

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role ENUM('Student', 'Staff') NOT NULL,
    course_or_department VARCHAR(255) NOT NULL
);

CREATE TABLE transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    bookId INT,
    userId INT,
    borrowDate DATE,
    returnDate DATE,
    FOREIGN KEY (bookId) REFERENCES books(id),
    FOREIGN KEY (userId) REFERENCES users(id)
);
