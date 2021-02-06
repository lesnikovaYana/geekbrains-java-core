package lesson3;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Orange[] orange = {new Orange(0.1f), new Orange(0.5f)};
        //int[] num = {1, 2, 3, 4};
        int i;
        int j;
        myArray(orange, 1, 0);

        Apple[] apple = {new Apple(5.1f), new Apple(8.0f), new Apple(7.8f)};
        Apple apple1 = new Apple(2.0f);
        Apple apple2 = new Apple(2.0f);

        Box<Apple> appleBox = new Box<Apple>(apple);
        Box<Apple> appleBox2 = new Box<Apple>(apple2);
        Box<Orange> orangeBox = new Box<Orange>(orange);

        appleBox.inform();
        appleBox.addBox(apple1);
        appleBox.inform();

        System.out.println("Вес коробки " + orangeBox.getWeight());

        System.out.println("Коробки равны - " + appleBox.compare(appleBox2));

        appleBox.pourBox(appleBox2);
        appleBox.inform();
        appleBox2.inform();

    }

    public static void myArray(Object[] objects, int i, int j){
        Object swap = objects[i];
        objects[i] = objects[j];
        objects[j] = swap;
        System.out.println(Arrays.toString(objects));
    }
}
