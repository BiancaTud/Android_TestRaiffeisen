package com.adonissoft.androidtestraiffeisen.userdetails;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adonissoft.androidtestraiffeisen.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bianca on 10/12/2017.
 */

public class UserDetailsView extends LinearLayout {

    private Context context;

    @BindView(R.id.tv_field_title)
    TextView tvFieldTitle;

    @BindView(R.id.tv_field_content)
    TextView tvFieldContent;


    private String fieldTitle;


    public UserDetailsView(Context context) {
        super(context);
        init(context);
    }

    public UserDetailsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.UserDetailsView, 0, 0);

        try {
            fieldTitle = a.getString(R.styleable.UserDetailsView_field_name);
        } finally {
            a.recycle();
        }

        init(context);

        if(fieldTitle!=null){
            setFieldTitle(fieldTitle);
        }
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_user_details, this, true);
        ButterKnife.bind(this);
    }


    public void setFieldTitle(String title){
        tvFieldTitle.setText(title);

    }

    public void setFieldContent(String content){
        tvFieldContent.setText(content);

    }
}
