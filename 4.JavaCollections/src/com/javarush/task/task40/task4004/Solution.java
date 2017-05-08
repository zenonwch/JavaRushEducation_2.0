package com.javarush.task.task40.task4004;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/* 
Принадлежность точки многоугольнику
*/

class Point {
    public int x;
    public int y;

    Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(final Point point, final List<Point> polygon) {
        if (polygon == null || point == null) return false;

        final int[] xArray = new int[polygon.size()];
        final int[] yArray = new int[polygon.size()];

        for(int i =0; i < polygon.size(); i++) {
            xArray[i] = polygon.get(i).x;
            yArray[i] = polygon.get(i).y;
        }

        final Polygon p = new Polygon(xArray, yArray, polygon.size());
        return p.contains(point.x, point.y);
    }

}

