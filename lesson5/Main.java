package lesson5;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here

        String[] header = {"Value 1", "Value 2", "Value 3"};
        int[][] data = {{100, 200, 123}, {300, 400, 500}};
        AppData appData = new AppData(header, data);
        System.out.println("До: " + appData);

        //файл создастся автоматически
        //сохранение данных в csv файл
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("table.csv")))) {
            for (int i = 0; i < appData.getHeader().length; i++) {
                writer.print(appData.getHeader()[i] + ";");
            }
            writer.println();
            for (int i = 0; i < appData.getData().length; i++) {
                for (int j = 0; j < appData.getData()[i].length; j++) {
                    writer.print(appData.getData()[i][j] + ";");
                }
                writer.println();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        //вывести данные в консоль и выполнить десириализацию
        ArrayList listHeader = new ArrayList<>();
        Scanner file = null;
        try {
            file = new Scanner(new File("table.csv")); //вызываем сканер для чтения файла
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        listHeader.add(file.nextLine().split(";"));

        String[] outputHeader = new String[0];
        for (int i = 0; i < listHeader.size(); i++) {
            outputHeader = (String[]) listHeader.get(i);
        }

        List<String> listData = new ArrayList<>();

        while (file.hasNext())
            listData.add(file.nextLine()); //считываем оставшиеся строки

        int[][] outputData = new int[listData.size()][];

        for (int i = 0; i < listData.size(); i++) {
            String currentString = listData.get(i);
            String[] split = currentString.split(";");
            outputData[i] = new int[split.length];
            for (int j = 0; j < split.length; j++) {
                outputData[i][j] = Integer.parseInt(split[j]);
            }
        }

        AppData appData1 = new AppData(outputHeader, outputData);
        System.out.println("После: " + appData1);

    }
}
