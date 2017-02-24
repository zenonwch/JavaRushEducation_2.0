package com.javarush.task.task05.task0521;

/* 
Вызов конструктора из конструктора
*/

public class Circle {

    private final double x;
    private final double y;
    private final double radius;

    public Circle(final double x, final double y, final double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public Circle(final double x, final double y) {
        this(x, y, 10);
    }

    public Circle() {
        this(5, 5, 1);
    }

    public static void main(final String[] args) {
        final Circle circle = new Circle();
        System.out.println(circle.x + " " + circle.y + ' ' + circle.radius);
        final Circle anotherCircle = new Circle(10, 5);
        System.out.println(anotherCircle.x + " " + anotherCircle.y + ' ' + anotherCircle.radius);
    }
}
