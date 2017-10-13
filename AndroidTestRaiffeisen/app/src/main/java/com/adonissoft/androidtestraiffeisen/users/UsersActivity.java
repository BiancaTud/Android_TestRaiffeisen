package com.adonissoft.androidtestraiffeisen.users;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adonissoft.androidtestraiffeisen.R;
import com.adonissoft.androidtestraiffeisen.api.model.User;
import com.adonissoft.androidtestraiffeisen.api.model.UserDetails;
import com.adonissoft.androidtestraiffeisen.userdetails.UserDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersActivity extends AppCompatActivity implements UsersContract.View {

    @BindView(R.id.users_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.users_progressbar)
    ProgressBar progressBar;

    @BindView(R.id.top_bar)
    Toolbar toolbar;

    UsersAdapter adapter;

    private UsersContract.Presenter presenter;

    private static final int MAX_PAGES = 3;
    public static final String USER_KEY = "user_key";

    private boolean userScrolled;
    private boolean previousPageLoaded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);

        setUpActionBar();
        setUpViews();
        setUpPresenter();

    }

    private void setUpActionBar() {
        toolbar.setTitle(getResources().getString(R.string.users));
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_search);
        toolbar.setOverflowIcon(drawable);
        Drawable drawableHome = ContextCompat.getDrawable(getApplicationContext(),R.mipmap.ic_menu);
        toolbar.setNavigationIcon(drawableHome);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.users_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void setUpPresenter() {
        presenter = new UsersPresenter(this);
        presenter.getUsers();
    }

    private void setUpViews() {
        adapter = new UsersAdapter(this);

        adapter.setUserClickListener(new UsersAdapter.UserClickListener() {
            @Override
            public void onUserClicked(UserDetails userDetails) {
                Intent intent = new Intent(UsersActivity.this, UserDetailsActivity.class);
                intent.putExtra(USER_KEY, userDetails);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        setRecyclerViewScrollListener();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateUsersList(List<User> users) {
        adapter.updateUsers(users);
        previousPageLoaded = true;
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
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
