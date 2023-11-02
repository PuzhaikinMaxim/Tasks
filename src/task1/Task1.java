package task1;

import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        int m = Integer.parseInt(args[1]);
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
