package lesson1;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Human man = new Human("Борис");
        Human man2 = new Human("Алексей");
        Human man3 = new Human("Женя");
        Treadmill treadmill = new Treadmill();
        Wall wall = new Wall();
        Treadmill.run(new Object[]{man,man2,man3}, new int[] {1, 4, 5}, new int[] {5, 2, 3});
        Wall.jump(new Object[]{man,man2,man3}, new int[] {6, 8, 1}, new int[] {5, 7, 3});
    }
}
