package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

public class UsersView implements View {
    private Controller controller;

    @Override
    public void refresh(final ModelData modelData) {
        System.out.println("All users:");
        for(final User user : modelData.getUsers()) {
            System.out.println("\t" + user);
        }
        System.out.println("==================================================");
    }

    @Override
    public void setController(final Controller controller) {
        this.controller = controller;
    }

    public void fireEventShowAllUsers() {
        controller.onShowAllUsers();
    }
}
