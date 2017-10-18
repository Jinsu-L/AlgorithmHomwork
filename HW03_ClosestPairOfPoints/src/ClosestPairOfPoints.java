import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ClosestPairOfPoints {
    public static void main(String[] args) throws Exception {
        String filepath = "./closest_data.txt";
        FileReader file = new FileReader(filepath);
        Scanner scanner = new Scanner(file);
        ArrayList<Point> list = new ArrayList<>();

        while (scanner.hasNext()) {
            Point tmp = new Point();
            tmp.x = scanner.nextDouble();
            tmp.y = scanner.nextDouble();
            list.add(tmp);
        }

        list.sort(Comparator.comparing(Point::getX));

        System.out.println(closestPair(list));

    }

    private static double closestPair(List<Point> points) {
        if (points.size() < 4) {
            double delta = Double.MAX_VALUE;

            for (int i = 0; i < points.size() - 1; i++) {
                for (int j = i + 1; j < points.size(); j++) {
                    double tmp = getDistance(points.get(i), points.get(j));
                    if (delta > tmp)
                        delta = tmp;
                }
            }

            return delta;
        }

        int mid = points.size() / 2;
        double left = closestPair(points.subList(0, mid));
        double right = closestPair(points.subList(mid, points.size()));
        double delta = Math.min(left, right);

        ArrayList<Point> remainPoints = new ArrayList<>();

        for (Point point : points)
            if (Math.abs(point.x - points.get(mid).x) <= delta)
                remainPoints.add(point);

        remainPoints.sort(Comparator.comparing(Point::getY));

        for (int i = 0; i < remainPoints.size(); i++) {
            for (int j = i + 1; j < i + 12 && j < remainPoints.size(); j++) {
                double tmp = getDistance(remainPoints.get(i), remainPoints.get(j));
                if (tmp < delta) {
                    delta = tmp;
                }
            }
        }

        return delta;
    }

    private static double getDistance(Point p1, Point p2) {
        return Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));
    }

    private static class Point {
        double x;
        double y;

        double getX() {
            return x;
        }

        double getY() {
            return y;
        }
    }
}