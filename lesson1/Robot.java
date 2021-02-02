package lesson1;

public class Robot implements Movement{
    @Override
    public void run() {
        System.out.println("Робот бежит.");
    }

    @Override
    public void jump() {
        System.out.println("Робот прыгает.");
    }
}
