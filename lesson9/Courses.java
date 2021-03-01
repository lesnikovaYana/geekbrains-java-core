package lesson9;

import java.util.Objects;

public class Courses implements Course{
    private String name;

    public Courses(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courses courses = (Courses) o;
        return Objects.equals(name, courses.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
