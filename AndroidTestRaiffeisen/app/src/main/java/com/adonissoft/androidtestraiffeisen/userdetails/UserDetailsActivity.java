package com.adonissoft.androidtestraiffeisen.userdetails;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adonissoft.androidtestraiffeisen.R;
import com.adonissoft.androidtestraiffeisen.api.model.User;
import com.adonissoft.androidtestraiffeisen.api.model.UserDetails;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.adonissoft.androidtestraiffeisen.users.UsersActivity.USER_KEY;

public class UserDetailsActivity extends AppCompatActivity {

    @BindView(R.id.field_user_phone)
    UserDetailsView userDetailsPhoneView;

    @BindView(R.id.field_user_email)
    UserDetailsView userDetailsEmailView;

    @BindView(R.id.field_user_address)
    UserDetailsView userDetailsAddressView;

    @BindView(R.id.tv_user_id)
    TextView tvUserDetailsId;

    @BindView(R.id.iv_user_pic)
    CircleImageView ivUserPic;

    @BindView(R.id.tv_user_name)
    TextView tvUserName;

    @BindView(R.id.top_bar)
    Toolbar toolbar;

    ScaleGestureDetector scaleGestureDetector;
    private String imageUrl = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);

        UserDetails currentUser = null;

        setUpActionBar();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentUser = (UserDetails) getIntent().getSerializableExtra(USER_KEY);
        }

        if (currentUser != null) {
            setUpViews(currentUser);
        }
    }

    private void setUpActionBar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.users_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


    private void setUpViews(final UserDetails currentUser) {
        userDetailsPhoneView.setFieldContent(currentUser.getPhone());
        userDetailsEmailView.setFieldContent(currentUser.getEmail());
        userDetailsAddressView.setFieldContent(currentUser.getAddress());
        tvUserDetailsId.setText(currentUser.getId());

        imageUrl = currentUser.getPictureUrl();
        Picasso.with(this).load(imageUrl).placeholder(R.mipmap.ic_placeholder).into(ivUserPic);
        tvUserName.setText(currentUser.getName());

        userDetailsEmailView.setUserDetailsClickListener(new UserDetailsView.UserDetailsClickListener() {
            @Override
            public void onUserDetailsClicked(String details) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:" + details + "?subject=" + "" + "&body=" + "");
                intent.setData(data);
                startActivity(Intent.createChooser(intent, ""));
            }
        });

        userDetailsPhoneView.setUserDetailsClickListener(new UserDetailsView.UserDetailsClickListener() {
            @Override
            public void onUserDetailsClicked(String details) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + details)));
            }
        });

        userDetailsAddressView.setUserDetailsClickListener(new UserDetailsView.UserDetailsClickListener() {
            @Override
            public void onUserDetailsClicked(String details) {
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(details));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        scaleGestureDetector =
                new ScaleGestureDetector(this,
                        new MyOnScaleGestureListener());

        ivUserPic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scaleGestureDetector.onTouchEvent(event);
                return true;
            }

        });


    }


    public class MyOnScaleGestureListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            ivUserPic.buildDrawingCache();

            Intent intent = new Intent(UserDetailsActivity.this, FullscreenImageActivity.class);
            intent.putExtra("BitmapImage", imageUrl);
            startActivity(intent);
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }

}
