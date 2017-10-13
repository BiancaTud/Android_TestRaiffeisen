package com.adonissoft.androidtestraiffeisen.users;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adonissoft.androidtestraiffeisen.R;
import com.adonissoft.androidtestraiffeisen.api.model.User;
import com.adonissoft.androidtestraiffeisen.api.model.UserDetails;
import com.hannesdorfmann.annotatedadapter.annotation.ViewField;
import com.hannesdorfmann.annotatedadapter.annotation.ViewType;
import com.hannesdorfmann.annotatedadapter.support.recyclerview.SupportAnnotatedAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Bianca on 12.10.2017.
 */

public class UsersAdapter extends SupportAnnotatedAdapter implements UsersAdapterBinder{

    List<User> listAccounts;
    Context context;
    UserClickListener userClickListener;

    @ViewType(
            layout = R.layout.item_user,
            views =
                    {
                            @ViewField(id = R.id.iv_user_pic, type = CircleImageView.class, name = "userPic"),
                            @ViewField(id = R.id.tv_user_name, type = TextView.class, name = "userName"),
                            @ViewField(id = R.id.tv_user_details, type = TextView.class, name = "userDetails"),
                            @ViewField(id = R.id.tv_user_timestamp, type = TextView.class, name = "userTimestamp"),
                            @ViewField(id = R.id.user_layout, type = LinearLayout.class, name = "userLayout")

                    }
    )
    public final int userPager = 0;



    public UsersAdapter(Context context) {
        super(context);
        this.context = context;
        listAccounts = new ArrayList<>();
    }

    public void setUserClickListener(UserClickListener userClickListener){
        this.userClickListener = userClickListener;
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

        String name = null;

        final User currentUser = listAccounts.get(position);

        if (currentUser.getPicture().getThumbnailPicUrl()!=null)
            Picasso.with(context).load(currentUser.getPicture().getThumbnailPicUrl()).placeholder(R.mipmap.ic_placeholder).into(vh.userPic);

        if(currentUser.getName()!=null){
            name = currentUser.getName().getFirstName().substring(0,1).toUpperCase() + currentUser.getName().getFirstName().substring(1) +
                    " " +  currentUser.getName().getLastName().substring(0,1).toUpperCase() + currentUser.getName().getLastName().substring(1);
            vh.userName.setText(name);
        }

        if(currentUser.getDob()!=null && currentUser.getNat()!=null){
            vh.userDetails.setText(currentUser.getNat());
        }

        if(currentUser.getRegistered()!=null){

            String[] arrayStringDate = currentUser.getRegistered().split(" ");
            String timestamp = null;
            if(arrayStringDate.length > 1){
                String[] arrayStringTime = arrayStringDate[1].split(":");
                if(arrayStringTime.length > 2){
                    timestamp = arrayStringTime[0] + ":" + arrayStringTime[1];
                }
            }

            if(timestamp!=null) {
                vh.userTimestamp.setText(timestamp);
            }
        }

        final String finalName = name;
        vh.userLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userClickListener!=null){

                    String username = finalName;
                    String pictureUrl = null, phone=null, email = null, address=null, id=null;

                    if(currentUser.getPicture().getMediumPicUrl()!=null){
                        pictureUrl = currentUser.getPicture().getMediumPicUrl();
                    }

                    if(currentUser.getPhone()!=null){
                        phone = currentUser.getPhone();
                    }

                    if(currentUser.getEmail()!=null){
                        email = currentUser.getEmail();
                    }

                    if(currentUser.getLocation()!=null){
                        address = currentUser.getLocation().getCity()+ ", " + currentUser.getLocation().getStreet();
                    }

                    if(currentUser.getId()!=null){
                        id = "ID: "+ currentUser.getId().getName() + " " + currentUser.getId().getValue();
                    }

                    UserDetails userDetails = new UserDetails(username, phone, email, address, id, pictureUrl);
                    userClickListener.onUserClicked(userDetails);
                }
            }
        });

    }




    public interface UserClickListener{
        void onUserClicked(UserDetails user);
    }
}
