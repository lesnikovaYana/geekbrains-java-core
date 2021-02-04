package lesson3;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int[] num = {1, 2, 3, 4};
        int i;
        int j;
        myArray(num, 2, 3);

    }

    public static void myArray(int[] num, int i, int j){
        int swap = num[i];
        num[i] = num[j];
        num[j] = swap;
        System.out.println(Arrays.toString(num)); 
    }
}
