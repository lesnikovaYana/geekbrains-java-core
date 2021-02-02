package lesson1;

public class Cat implements Movement{
    @Override
    public void run() {
        System.out.println("Кошка бежит.");
    }

    @Override
    public void jump() {
        System.out.println("Кошка прыгает.");
    }
}
