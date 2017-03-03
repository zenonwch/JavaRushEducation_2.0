package com.javarush.task.task36.task3608.controller;

import com.javarush.task.task36.task3608.model.Model;
import com.javarush.task.task36.task3608.model.ModelData;
import com.javarush.task.task36.task3608.view.UsersView;

public class Controller {
    private Model model;
    private UsersView usersView;

    public void setModel(final Model model) {
        this.model = model;
    }

    public void setUsersView(final UsersView usersView) {
        this.usersView = usersView;
    }

    public void onShowAllUsers() {
        model.loadUsers();
        final ModelData modelData = model.getModelData();
        usersView.refresh(modelData);
    }

    public void onShowAllDeletedUsers() {
        model.loadDeletedUsers();
        final ModelData modelData = model.getModelData();
        usersView.refresh(modelData);
    }
}
