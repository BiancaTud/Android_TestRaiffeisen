package com.adonissoft.androidtestraiffeisen.userdetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.adonissoft.androidtestraiffeisen.R;
import com.adonissoft.androidtestraiffeisen.api.model.User;
import com.adonissoft.androidtestraiffeisen.api.model.UserDetails;

import static com.adonissoft.androidtestraiffeisen.users.UsersActivity.USER_KEY;

public class UserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        UserDetails currentUser  =null;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentUser = (UserDetails) getIntent().getSerializableExtra(USER_KEY); //Obtaining data
        }

        if(currentUser!=null){

        }
    }
}
