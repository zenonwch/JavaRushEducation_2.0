package com.javarush.task.task39.task3906;

/* 
Интерфейсы нас спасут!
*/
public class Solution {
    public static void main(final String[] args) {
        final SecuritySystem securitySystem = new SecuritySystem();
        final LightBulb lightBulb = new LightBulb();
        final ElectricPowerSwitch securitySystemSwitch = new ElectricPowerSwitch(securitySystem);
        final ElectricPowerSwitch lightBulbSwitch = new ElectricPowerSwitch(lightBulb);

        securitySystemSwitch.press();
        securitySystemSwitch.press();
        lightBulbSwitch.press();
    }
}
