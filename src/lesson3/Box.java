package lesson3;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {
    private ArrayList<T> fruitList; //храним фрукт

    public Box(T...fruitList) {
        this.fruitList = new ArrayList<T>(Arrays.asList(fruitList)); //конструктор
    }

    public void addBox(T fruit){
        fruitList.add(fruit); //добавляем фрукт
    }

    public float getWeight(){
        if(fruitList.size() == 0) return 0; //взвешиваем коробку
        float weight = 0;
        for (T fruit: fruitList) {
            weight = weight + fruit.getWeight();
        }
        return weight;
    }

    public boolean compare(Box<? extends Fruit> anotherBox) { //сравниваем коробки

        return this.getWeight() == anotherBox.getWeight();
    }

    public void pourBox(Box<? super T> anotherBox) {      //пересыпаем из коробки
        anotherBox.fruitList.addAll(this.fruitList);
        fruitList.clear();
    }

    public void inform() {
        System.out.println("В коробке " + fruitList.size() + " шт. фруктов.");
    }
}
