package com.adonissoft.androidtestraiffeisen.users;

import com.adonissoft.androidtestraiffeisen.api.ApiResponse;
import com.adonissoft.androidtestraiffeisen.api.ApiServiceFactory;
import com.adonissoft.androidtestraiffeisen.api.RaiffeisenApiService;
import com.adonissoft.androidtestraiffeisen.api.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bianca on 12.10.2017.
 */

public class UsersPresenter implements UsersContract.Presenter{

    private UsersContract.View view;

    private static final int STARTING_PAGE_NR = 0;
    private static final int RESULTS = 100;
    private static final String SEED = "abc";
    private int pageNr;
    private RaiffeisenApiService apiService;


    public UsersPresenter(UsersContract.View v ) {
        view = v;
        pageNr = STARTING_PAGE_NR;
        apiService = ApiServiceFactory.createRaiffeisenApiService();
    }

    @Override
    public void getUsers() {
        apiService.getUsers(pageNr, RESULTS, SEED,
                new Callback<ApiResponse>() {
                    @Override
                    public void success(ApiResponse apiResponse, Response response) {
                        view.hideProgress(pageNr == STARTING_PAGE_NR);
                        List<User> users = apiResponse.getResults();
                        view.updateUsersList(users);
                        pageNr++;
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        view.hideProgress(pageNr == STARTING_PAGE_NR);
                        view.showError();
                    }
                });
    }

    @Override
    public int getPageNr() {
        return pageNr;
    }
}
