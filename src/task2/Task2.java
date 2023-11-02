package task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Task2 {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Circle circle = getCircle(args[0]);
        List<Point> points = getPoints(args[1]);
        for(Point point : points){
            Point displaced = new Point(point.x - circle.coordinates.x, point.y - circle.coordinates.y);
            float distanceToZeroCoords = (float) Math.sqrt(displaced.x*displaced.x + displaced.y*displaced.y);
            if(distanceToZeroCoords == circle.radius) System.out.println(0);
            if(distanceToZeroCoords < circle.radius) System.out.println(1);
            if(distanceToZeroCoords > circle.radius) System.out.println(2);
        }
    }

    private static Circle getCircle(String location) {
        Circle circle = new Circle();
        try {
            BufferedReader file1Reader = new BufferedReader(new FileReader(location));
            String file1 = file1Reader.readLine();
            Float[] coords = Arrays.stream(file1.split(" ")).map(Float::parseFloat).toArray(Float[]::new);
            circle.coordinates = new Point(coords[0], coords[1]);
            String file2 = file1Reader.readLine();
            circle.radius = Float.parseFloat(file2);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return circle;
    }

    private static List<Point> getPoints(String location) {
        ArrayList<Point> points = new ArrayList<>();
        try {
            BufferedReader file2Reader = new BufferedReader(new FileReader(location));
            String line = file2Reader.readLine();
            while (line != null){
                Float[] coords = Arrays.stream(line.split(" ")).map(Float::parseFloat).toArray(Float[]::new);
                points.add(new Point(coords[0], coords[1]));
                line = file2Reader.readLine();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return points;
    }

    private static class Circle {
        Point coordinates;

        float radius;
    }

    private static class Point {
        float x;

        float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}
