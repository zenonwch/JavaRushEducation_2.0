package com.javarush.task.task36.task3609;

/* 
Рефакторинг MVC
*/

public class Solution {
    public static void main(final String[] args) {
        //Fetch car record from the database
        final CarModel model = new CarModel("Nissan", "Almera classic", 0, 200);

        //Create a view : to show car's speed on speedometer(console)
        final SpeedometerView view = new SpeedometerView();

        final CarController controller = new CarController(model, view);
        controller.updateView();

        //Update model data
        controller.speedUp(15);
        controller.updateView();

        //Update model data
        controller.speedUp(50);
        controller.updateView();

        //Update model data
        controller.speedDown(7);
        controller.updateView();
    }
}