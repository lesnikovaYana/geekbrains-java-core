package lesson4;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String[]fruit = {"Лимон", "Абрикос", "Груша", "Яблоко", "Хурма", "Гранат", "Персик","Гранат", "Лимон", "Апельсин"};
        ArrayList<String> fruitList = new ArrayList<>(Arrays.asList(fruit));

        //выводим массив без дубликатов
        Set fruitSet = new LinkedHashSet();
        fruitSet.addAll(fruitList);
        System.out.println(fruitSet);

        //узнаём число дубликатов
        Map<String, Integer> fruitMap = new HashMap<>();
        checkDuplicates(fruitList, fruitMap);
        stringDuplicates(fruitMap);

        //телефонная книжка
        Phonebook phonebook = new Phonebook();
        phonebook.add("Коровин", "89386996690");
        phonebook.add("Коровин", "89368211671");
        phonebook.add("Володин", "+79876995550");
        System.out.println(phonebook.get("Коровин"));
        System.out.println(phonebook.getPhonebook());
    }

    private static void stringDuplicates(Map<String, Integer> fruitMap) {
        for (Map.Entry<String, Integer> entry : fruitMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    private static void checkDuplicates(ArrayList<String> fruitList, Map<String, Integer> fruitMap) {
        for (String str : fruitList) {
            if (fruitMap.containsKey(str)) {
                fruitMap.put(str, fruitMap.get(str) + 1);
            } else {
                fruitMap.put(str, 1);
            }
        }
    }
}
