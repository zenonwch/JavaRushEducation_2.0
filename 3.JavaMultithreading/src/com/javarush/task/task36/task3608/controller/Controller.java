package com.javarush.task.task36.task3608.controller;

import com.javarush.task.task36.task3608.model.Model;
import com.javarush.task.task36.task3608.model.ModelData;
import com.javarush.task.task36.task3608.view.EditUserView;
import com.javarush.task.task36.task3608.view.UsersView;

public class Controller {
    private Model model;
    private UsersView usersView;
    private EditUserView editUserView;

    public void setModel(final Model model) {
        this.model = model;
    }

    public void setUsersView(final UsersView usersView) {
        this.usersView = usersView;
    }

    public void setEditUserView(final EditUserView editUserView) {
        this.editUserView = editUserView;
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

    public void onOpenUserEditForm(final long userId) {
        model.loadUserById(userId);
        final ModelData modelData = model.getModelData();
        editUserView.refresh(modelData);
    }

    public void onUserDelete(final long userId) {
        model.deleteUserById(userId);
        final ModelData modelData = model.getModelData();
        usersView.refresh(modelData);
    }
}
