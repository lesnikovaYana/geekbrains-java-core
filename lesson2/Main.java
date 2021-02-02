package lesson2;

public class Main {

    public static void main(String[] args) {

        myArray(new String[4][4]);
        myArray(new String[5][4]);
        myArray(new String[4][3]);
        myArray(new String[][]{
                {"8", "2", "6", "4"},
                {"1", "9", "5", "40"},
                {"6", "1", "3", "4"},
                {"1", "20", "ваш", "28"}
        });
        myArray(new String[][]{
                {"8", "2", "6", "4"},
                {"1", "9", "5", "40"},
                {"6", "1", "3", "4"},
                {"1", "20", "30", "28"}
        });

    }

    public static void myArray(String[][] strings) {
        try {
            checkArraySize(strings);
            calcArrays(strings);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static void checkArraySize(String[][] strings) throws MyArraySizeException {
        for (int i = 0; i < strings.length; i++) {
            if (strings.length != 4) {
                throw new MyArraySizeException("Неверное количество строк.");
            }
            if (strings[i].length != 4) {
                throw new MyArraySizeException("Неверное количество столбцов.");
            }
        }
    }

    public static void calcArrays(String[][] strings) throws MyArrayDataException {
        int sum = 0;
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings.length; j++) {
                try {
                    sum = sum + Integer.parseInt(strings[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(String.format("Ошибка в ячейке " + strings[i][j] + " строки " + i + " столбца " + j));
                }
            }
        }
        System.out.println(sum);
    }

}



