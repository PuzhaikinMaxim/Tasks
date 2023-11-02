package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task4 {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String location = args[0];
        List<Integer> values = getValues(location);
        Integer averageValue = values.stream().mapToInt(Integer::intValue).sum()/values.size();
        Double leastDifference = Double.MAX_VALUE;
        Integer leastDifferenceNumber = 0;
        for(Integer value : values) {
            Double valueD = value.doubleValue();
            Double difference = Math.max(averageValue - valueD, valueD - averageValue);
            if(leastDifference > difference){
                leastDifference = difference;
                leastDifferenceNumber = value;
            }
        }
        int minimalTurns = 0;
        for(Integer value : values) {
            minimalTurns += Math.max(leastDifferenceNumber - value, value - leastDifferenceNumber);
        }
        System.out.println(minimalTurns);
    }

    private static List<Integer> getValues(String location) {
        ArrayList<Integer> values = new ArrayList<>();
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(location));
            String line = fileReader.readLine();
            while (line != null){
                values.add(Integer.parseInt(line));
                line = fileReader.readLine();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return values;
    }
}
