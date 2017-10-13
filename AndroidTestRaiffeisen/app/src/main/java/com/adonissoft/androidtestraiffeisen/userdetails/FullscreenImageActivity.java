package com.adonissoft.androidtestraiffeisen.userdetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.adonissoft.androidtestraiffeisen.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FullscreenImageActivity extends AppCompatActivity {

    @BindView(R.id.iv_fullscreen)
    ImageView ivUserPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            String imageUrl = getIntent().getExtras().getString("BitmapImage");
            Picasso.with(this).load(imageUrl).placeholder(R.mipmap.ic_placeholder).into(ivUserPic);
        }
    }

}
