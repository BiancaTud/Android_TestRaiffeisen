package com.adonissoft.androidtestraiffeisen.users;

import com.adonissoft.androidtestraiffeisen.api.ApiResponse;
import com.adonissoft.androidtestraiffeisen.api.ApiServiceFactory;
import com.adonissoft.androidtestraiffeisen.api.RaiffeisenApiService;
import com.adonissoft.androidtestraiffeisen.api.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UsersPresenter implements UsersContract.Presenter {

    private UsersContract.View view;

    private static final int STARTING_PAGE_NR = 0;
    private static final int RESULTS = 10;
    private static final String SEED = "abc";
    private int pageNr;
    private RaiffeisenApiService apiService;


    public UsersPresenter(UsersContract.View v) {
        view = v;
        pageNr = STARTING_PAGE_NR;
        apiService = ApiServiceFactory.createRaiffeisenApiService();
    }

    @Override
    public void getUsers() {

        view.showProgress();

        apiService.getUsers(pageNr, RESULTS, SEED,
                new Callback<ApiResponse>() {
                    @Override
                    public void success(ApiResponse apiResponse, Response response) {
                        view.hideProgress();
                        List<User> users = apiResponse.getResults();
                        view.updateUsersList(users);
                        pageNr++;
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        view.hideProgress();
                        view.showError(error.getMessage().toString());
                    }
                });
    }

    @Override
    public int getPageNr() {
        return pageNr;
    }
}
