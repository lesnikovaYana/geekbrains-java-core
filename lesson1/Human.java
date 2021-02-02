package lesson1;

public class Human implements Movement{
    private String name;

    public Human () {

    }

    public Human(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Человек бежит.");
    }

    @Override
    public void jump() {
        System.out.println("Человек прыгает.");
    }

    @Override
    public String toString() {
        return name;
    }
}
