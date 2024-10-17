package com.library.models;

public class Staff extends User implements Borrower{
    private String department;

    public Staff(int id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
    }

    @Override
    public String getRole() {
        return "Staff";
    }

    @Override
    public void borrowBook() {
        System.out.println("Staff borrowing a book...");
    }

    @Override
    public void returnBook() {
        System.out.println("Staff returning a book...");
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
