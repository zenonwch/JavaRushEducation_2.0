package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.List;

public class MainModel implements Model {
    private ModelData modelData = new ModelData();
    private UserService userService = new UserServiceImpl();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        final List<User> users = getAllUsers();
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(users);
    }

    @Override
    public void loadDeletedUsers() {
        final List<User> users = userService.getAllDeletedUsers();
        modelData.setDisplayDeletedUserList(true);
        modelData.setUsers(users);
    }

    @Override
    public void loadUserById(final long userId) {
        final User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }

    @Override
    public void deleteUserById(final long userId) {
        userService.deleteUser(userId);
        final List<User> users = getAllUsers();
        modelData.setUsers(users);
    }

    @Override
    public void changeUserData(final String name, final long userId, final int level) {
        userService.createOrUpdateUser(name, userId, level);
        final List<User> users = getAllUsers();
        modelData.setUsers(users);
    }

    private List<User> getAllUsers() {
        final List<User> allUsers = userService.getUsersBetweenLevels(1, 100);
        return userService.filterOnlyActiveUsers(allUsers);
    }
}
