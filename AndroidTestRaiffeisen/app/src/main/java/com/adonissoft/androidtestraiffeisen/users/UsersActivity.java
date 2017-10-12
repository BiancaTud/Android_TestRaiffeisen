package com.adonissoft.androidtestraiffeisen.users;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.adonissoft.androidtestraiffeisen.R;
import com.adonissoft.androidtestraiffeisen.api.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersActivity extends AppCompatActivity implements UsersContract.View {

    @BindView(R.id.users_recycler_view)
    RecyclerView recyclerView;

    UsersAdapter adapter;

    private UsersContract.Presenter presenter;

    private static final int MAX_PAGES = 3;

    private boolean userScrolled;
    private boolean previousPageLoaded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);
        setUpViews();
        setUpPresenter();

    }

    private void setUpPresenter() {
        presenter = new UsersPresenter(this);
        presenter.getUsers();
    }

    private void setUpViews() {
        adapter = new UsersAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        setRecyclerViewScrollListener();
    }

    @Override
    public void showProgress(boolean isFirstProgressLoad) {

    }

    @Override
    public void hideProgress(boolean isFirstProgressLoad) {

    }

    @Override
    public void updateUsersList(List<User> users) {
        adapter.updateUsers(users);
        previousPageLoaded = true;
    }

    @Override
    public void showError() {

    }


    private void setRecyclerViewScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    userScrolled = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = llm.getChildCount();
                int totalItemCount = llm.getItemCount();
                int pastVisibleItemCount = llm.findFirstVisibleItemPosition();
                if (userScrolled
                        && previousPageLoaded
                        && (visibleItemCount + pastVisibleItemCount) >= totalItemCount
                        && presenter.getPageNr() < MAX_PAGES) {
                    userScrolled = false;
                    previousPageLoaded = false;
                    presenter.getUsers();
                }
            }
        });
    }
}
