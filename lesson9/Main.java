package lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // write your code here

        List<Course> listCourses1 = new ArrayList<>();
        listCourses1.add(new Courses("Курсы Мозаики"));
        listCourses1.add(new Courses("Курсы Рисования"));
        listCourses1.add(new Courses("Курсы Пирографии"));
        listCourses1.add(new Courses("Курсы Оригами"));

        List<Course> listCourses2 = new ArrayList<>();
        listCourses2.add(new Courses("Курсы Вязания"));
        listCourses2.add(new Courses("Курсы Вышивания"));

        List<Course> listCourses3 = new ArrayList<>();
        listCourses3.add(new Courses("Курсы Рисования"));
        listCourses3.add(new Courses("Курсы Оригами"));

        List<Course> listCourses4 = new ArrayList<>();
        listCourses4.add(new Courses("Курсы Пирографии"));

        List<Student> studentList = new ArrayList<>(Arrays.asList(
                new Students("Станислав", listCourses1),
                new Students("Мария", listCourses2),
                new Students("Алёна", listCourses3),
                new Students("Сергей", listCourses4)
        ));

        System.out.println();
        uniqueCourses(studentList).forEach(System.out::println);
        System.out.println();

        System.out.println();
        theMostActive(studentList).forEach(System.out::println);
        System.out.println();

        System.out.println();
        Courses course = new Courses("Курсы Рисования");
        onOneCourse(studentList, course).forEach(System.out::println);
        System.out.println();

    }

    public static List<Course> uniqueCourses(List<Student> studentList) {
        return studentList.stream()
                .map(Student::getAllCourses)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Student> theMostActive(List<Student> studentList) {
        return studentList.stream()
                .sorted((a1, a2) -> a2.getAllCourses().size() - a1.getAllCourses().size())
                .limit(3)
                .collect(Collectors.toList());
    }

    public static List<Student> onOneCourse(List<Student> studentList, Courses course) {
        return studentList.stream()
                .filter(a -> a.getAllCourses().contains(course))
                .collect(Collectors.toList());
    }
}
