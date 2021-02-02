package lesson1;

public class Wall {
    private int height;

    public static void jump (Object[] participant, int[] bounce, int[] height) {
        for (int j = 0;  j< participant.length; j++) {
            for (int i = 0; i < bounce.length; i++) {
                if (bounce[j] >= height[i]) {
                    System.out.println(participant[j] + " успешно прыгнул на " + height[i] + " м");
                } else {
                    System.out.println(participant[j] + " не смог прыгнуть на " + height[i] + " м");
                }
            }
        }
    }

}
