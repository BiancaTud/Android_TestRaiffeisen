package com.adonissoft.androidtestraiffeisen.users;

import android.content.Context;
import android.widget.TextView;

import com.adonissoft.androidtestraiffeisen.R;
import com.adonissoft.androidtestraiffeisen.api.model.User;
import com.hannesdorfmann.annotatedadapter.annotation.ViewField;
import com.hannesdorfmann.annotatedadapter.annotation.ViewType;
import com.hannesdorfmann.annotatedadapter.support.recyclerview.SupportAnnotatedAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Bianca on 12.10.2017.
 */

public class UsersAdapter extends SupportAnnotatedAdapter implements UsersAdapterBinder{

    List<User> listAccounts;
    Context context;


    @ViewType(
            layout = R.layout.item_user,
            views =
                    {
                            @ViewField(id = R.id.iv_user_pic, type = CircleImageView.class, name = "userPic"),
                            @ViewField(id = R.id.tv_user_name, type = TextView.class, name = "userName"),
                            @ViewField(id = R.id.tv_user_details, type = TextView.class, name = "userDetails"),
                            @ViewField(id = R.id.tv_user_timestamp, type = TextView.class, name = "userTimestamp")

                    }
    )
    public final int userPager = 0;



    public UsersAdapter(Context context) {
        super(context);
        this.context = context;
        listAccounts = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        if (listAccounts == null)
            return 0;
        return listAccounts.size();
    }


    public void setUsers(List<User> listAccounts) {
        this.listAccounts = listAccounts;
        notifyDataSetChanged();
    }

    public void updateUsers(List<User> users) {
        listAccounts.addAll(users);
        notifyDataSetChanged();
    }


    public void clearAllItems() {
        listAccounts.clear();
        notifyDataSetChanged();
    }


    public List<User> getUsers() {
        return this.listAccounts;
    }

    @Override
    public void bindViewHolder(UsersAdapterHolders.UserPagerViewHolder vh, int position) {

        User currentUser = listAccounts.get(position);

        if (currentUser.getPicture().getThumbnailPicUrl()!=null)
            Picasso.with(context).load(currentUser.getPicture().getThumbnailPicUrl()).placeholder(R.mipmap.ic_placeholder).into(vh.userPic);

        if(currentUser.getName()!=null){
            vh.userName.setText(currentUser.getName().getFirstName() + " " +  currentUser.getName().getLastName());
        }

        if(currentUser.getNat()!=null){
            vh.userDetails.setText(currentUser.getNat());
        }

        if(currentUser.getRegistered()!=null){
            vh.userTimestamp.setText(currentUser.getRegistered());
        }

    }
}
