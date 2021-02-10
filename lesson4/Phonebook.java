package lesson4;

import java.util.ArrayList;
import java.util.HashMap;

public class Phonebook {
    private HashMap<String, ArrayList<String>> phonebook;

    public Phonebook() {
        this.phonebook = new HashMap<String, ArrayList<String>>();
    }

    public HashMap<String, ArrayList<String>> getPhonebook() {
        return phonebook;
    }

    public void add(String surname, String phone) {
        ArrayList<String> phoneList = new ArrayList<>();
        if (this.phonebook.containsKey(surname)) {
            phoneList = this.phonebook.get(surname);
        }
        phoneList.add(phone);
        this.phonebook.put(surname, phoneList);
    }

    public ArrayList<String> get(String surname) {
        return this.phonebook.get(surname);
    }
}

