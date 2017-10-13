package com.adonissoft.androidtestraiffeisen.users;

import com.adonissoft.androidtestraiffeisen.api.model.User;

import java.util.List;

public class UsersContract {


    public interface View {

        void showProgress();

        void hideProgress();

        void updateUsersList(List<User> users);

        void showError(String error);
    }

    public interface Presenter {

        void getUsers();

        int getPageNr();

    }
}
