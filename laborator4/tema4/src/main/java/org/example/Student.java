package org.example;

public class Student implements Comparable<Student> {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Student o) {
        return this.getName().compareTo(o.getName());
    }
}
