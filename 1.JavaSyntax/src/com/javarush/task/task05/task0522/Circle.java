package com.javarush.task.task05.task0522;

/* 
Максимум конструкторов
*/

public class Circle {
    private final double x;
    private final double y;
    private final double radius;

    public Circle() {
       this(1, 2, 3);
    }

    public Circle(final double x, final double y, final double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public Circle(final double x, final double y) {
        this(x, y, 3);
    }

    public Circle(final double radius) {
        this(1, 2, radius);
    }

    public static void main(final String[] args) {

    }
}