package task1;

import java.util.Scanner;

public class Task1 {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int m = sc.nextInt();
        m = Math.max(0, m - 1);

        int current = 1;
        boolean isAtStart = true;
        while (current != 1 || isAtStart) {
            System.out.println(current);
            current = current + m;
            if(current > n){
                current = current % n;
            }
            isAtStart = false;
        }
    }
}
