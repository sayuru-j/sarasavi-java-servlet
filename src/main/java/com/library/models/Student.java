package com.library.models;

public class Student extends User implements Borrower{
    private String course;

    public Student(int id, String name, String email, String course) {
        super(id, name, email);
        this.course = course;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public void borrowBook() {
        System.out.println("Student borrowing a book...");
    }

    @Override
    public void returnBook() {
        System.out.println("Student returning a book...");
    }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
}
