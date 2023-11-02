package task1;

import java.util.Arrays;
import java.util.Scanner;

public class Task1 {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int[] nm = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nm[0];

        int m = nm[1];
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
