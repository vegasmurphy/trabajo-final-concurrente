package servidor;
import Misiles.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CircleLine {

    public static List<Vector> getCircleLineIntersectionPoint(Vector pointA,
            Vector pointB, Vector center, double radius) {
        double baX = pointB.getX() - pointA.getX();
        double baY = pointB.getY() - pointA.getY();
        double caX = center.getX() - pointA.getX();
        double caY = center.getY() - pointA.getY();

        double a = baX * baX + baY * baY;
        double bBy2 = baX * caX + baY * caY;
        double c = caX * caX + caY * caY - radius * radius;

        double pBy2 = bBy2 / a;
        double q = c / a;

        double disc = pBy2 * pBy2 - q;
        if (disc < 0) {
            return Collections.emptyList();
        }
        // if disc == 0 ... dealt with later
        double tmpSqrt = Math.sqrt(disc);
        double abScalingFactor1 = -pBy2 + tmpSqrt;
        double abScalingFactor2 = -pBy2 - tmpSqrt;

        Vector p1 = new Vector(pointA.getX() - baX * abScalingFactor1, pointA.getY()
                - baY * abScalingFactor1,0);
        if (disc == 0) { // abScalingFactor1 == abScalingFactor2
            return Collections.singletonList(p1);
        }
        Vector p2 = new Vector(pointA.getX() - baX * abScalingFactor2, pointA.getY()
                - baY * abScalingFactor2,0);
        return Arrays.asList(p1, p2);
    }

    static class Point {
        double x, y;

        public Point(double x, double y) { this.x = x; this.y = y; }

        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + "]";
        }
    }}


