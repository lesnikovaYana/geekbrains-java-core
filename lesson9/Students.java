package lesson9;

import java.util.List;

public class Students implements Student{
    private String name;
    private List<Course> courses;

    public Students(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Students{" +
                "name='" + name + '\'' +
                ", courses=" + courses +
                '}';
    }
}
