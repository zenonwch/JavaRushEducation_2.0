package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

public class EditUserView implements View {
    private Controller controller;

    @Override
    public void refresh(final ModelData modelData) {
        System.out.println("User to be edited:");
        System.out.println("\t" + modelData.getActiveUser());
        System.out.println("===================================================");
    }

    @Override
    public void setController(final Controller controller) {
        this.controller = controller;
    }

    public void fireEventUserDeleted(final long id) {
        controller.onUserDelete(id);
    }

    public void fireEventUserChanged(final String name, final long id, final int level) {
        controller.onUserChange(name, id, level);
    }
}
