package com.adonissoft.androidtestraiffeisen.users;

import com.adonissoft.androidtestraiffeisen.api.model.User;

import java.util.List;

/**
 * Created by Bianca on 12.10.2017.
 */

public class UsersContract {


    public interface View {

        void showProgress(boolean isFirstProgressLoad);

        void hideProgress(boolean isFirstProgressLoad);

        void updateUsersList(List<User> users);


        void showError();
    }

    public interface Presenter {

        void getUsers();

        int getPageNr();

    }
}
