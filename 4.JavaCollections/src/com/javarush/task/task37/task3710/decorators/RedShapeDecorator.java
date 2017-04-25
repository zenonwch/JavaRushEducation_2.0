package com.javarush.task.task37.task3710.decorators;

import com.javarush.task.task37.task3710.shapes.Shape;

public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(final Shape decoratedShape) {
        super(decoratedShape);
    }

    private void setBorderColor(final Shape shape) {
        System.out.printf("Setting border color for %s to red.", shape.getClass().getSimpleName());
        System.out.println();
    }

    @Override
    public void draw() {
        setBorderColor(decoratedShape);
        super.draw();
    }
}
