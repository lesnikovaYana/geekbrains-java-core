package lesson1;

public class Treadmill {
    private int distance;

    public static void run (Object[] participant, int[] speed, int[] distance) {
        for (int j = 0;  j< participant.length; j++) {
            for (int i = 0; i < speed.length; i++) {
                if (speed[j] >= distance[i]) {
                    System.out.println(participant[j] + " успешно пробежал дистанцию " + distance[i] + " м");
                } else {
                    System.out.println(participant[j] + " не смог пробежать дистанцию " + distance[i] + " м");
                }
            }
        }
    }

}
