package lesson5;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        // write your code here

        String[] header = {"Value 1", "Value 2", "Value 3"};
        int[][] data = {{100, 200, 123}, {300, 400, 500}};
        AppData appData = new AppData(header, data);
        System.out.println("До: " + appData);

        //сериализация
        // запись в файл в байтовом представлении
        //файл создастся автоматически
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream("table.csv"));
             ObjectOutputStream objOut = new ObjectOutputStream(out)) {
            objOut.writeObject(appData);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        //десиариализация
        try (FileInputStream fileInput = new FileInputStream("table.csv");
             ObjectInputStream objIn = new ObjectInputStream(fileInput)) {
            AppData appDataIn = (AppData) objIn.readObject();
            System.out.println("После: " + appDataIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
