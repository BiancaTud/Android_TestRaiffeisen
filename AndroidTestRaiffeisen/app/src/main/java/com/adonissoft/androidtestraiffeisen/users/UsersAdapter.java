package com.adonissoft.androidtestraiffeisen.users;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adonissoft.androidtestraiffeisen.R;
import com.adonissoft.androidtestraiffeisen.api.model.User;
import com.adonissoft.androidtestraiffeisen.api.model.UserDetails;
import com.hannesdorfmann.annotatedadapter.annotation.ViewField;
import com.hannesdorfmann.annotatedadapter.annotation.ViewType;
import com.hannesdorfmann.annotatedadapter.support.recyclerview.SupportAnnotatedAdapter;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends SupportAnnotatedAdapter implements UsersAdapterBinder {

    List<User> listAccounts;
    Context context;
    UserClickListener userClickListener;

    private static String flagUrl = "http://www.geognos.com/api/en/countries/flag/";

    @ViewType(
            layout = R.layout.item_user,
            views =
                    {
                            @ViewField(id = R.id.iv_user_pic, type = CircleImageView.class, name = "userPic"),
                            @ViewField(id = R.id.tv_user_name, type = TextView.class, name = "userName"),
                            @ViewField(id = R.id.tv_user_details, type = TextView.class, name = "userDetails"),
                            @ViewField(id = R.id.tv_user_timestamp, type = TextView.class, name = "userTimestamp"),
                            @ViewField(id = R.id.iv_user_flag, type = ImageView.class, name = "userFlag"),
                            @ViewField(id = R.id.user_layout, type = LinearLayout.class, name = "userLayout")

                    }
    )
    public final int userPager = 0;


    public UsersAdapter(Context context) {
        super(context);
        this.context = context;
        listAccounts = new ArrayList<>();
    }

    public void setUserClickListener(UserClickListener userClickListener) {
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

        if (currentUser.getPicture().getThumbnailPicUrl() != null)
            Picasso.with(context).load(currentUser.getPicture().getThumbnailPicUrl()).placeholder(R.mipmap.ic_placeholder).into(vh.userPic);

        if (currentUser.getName() != null) {
            name = currentUser.getName().getFirstName().substring(0, 1).toUpperCase() + currentUser.getName().getFirstName().substring(1) +
                    " " + currentUser.getName().getLastName().substring(0, 1).toUpperCase() + currentUser.getName().getLastName().substring(1);
            vh.userName.setText(name);
        }

        if (currentUser.getDob() != null && currentUser.getNat() != null) {
            String[] arrayStringBirthdate = currentUser.getDob().split(" ");
            if (arrayStringBirthdate.length > 1) {
                String dob = arrayStringBirthdate[0];
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar dateOfBirth = Calendar.getInstance();
                try {
                    dateOfBirth.setTime(sdf.parse(dob));
                    vh.userDetails.setText(getAge(dateOfBirth) + " " + context.getString(R.string.years) + " " + context.getString(R.string.from) + " ");

                    Picasso.with(context).load(flagUrl + currentUser.getNat() + ".png").into(vh.userFlag);

                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        if (currentUser.getRegistered() != null) {

            String[] arrayStringDate = currentUser.getRegistered().split(" ");
            String timestamp = null;
            if (arrayStringDate.length > 1) {
                String[] arrayStringTime = arrayStringDate[1].split(":");
                if (arrayStringTime.length > 2) {
                    timestamp = arrayStringTime[0] + ":" + arrayStringTime[1];
                }
            }

            if (timestamp != null) {
                vh.userTimestamp.setText(timestamp);
            }
        }

        final String finalName = name;
        vh.userLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userClickListener != null) {

                    String username = finalName;
                    String pictureUrl = null, phone = null, email = null, address = null, id = null;

                    if (currentUser.getPicture().getLargePicUrl() != null) {
                        pictureUrl = currentUser.getPicture().getLargePicUrl();
                    }

                    if (currentUser.getPhone() != null) {
                        phone = currentUser.getPhone();
                    }

                    if (currentUser.getEmail() != null) {
                        email = currentUser.getEmail();
                    }

                    if (currentUser.getLocation() != null) {
                        address = currentUser.getLocation().getCity() + ", " + currentUser.getLocation().getStreet();
                    }

                    if (currentUser.getId() != null) {
                        id = "ID: " + currentUser.getId().getName() + " " + currentUser.getId().getValue();
                    }

                    UserDetails userDetails = new UserDetails(username, phone, email, address, id, pictureUrl);
                    userClickListener.onUserClicked(userDetails);
                }
            }
        });

    }

    public static int getAge(Calendar dob) throws Exception {
        Calendar today = Calendar.getInstance();

        int curYear = today.get(Calendar.YEAR);
        int dobYear = dob.get(Calendar.YEAR);

        int age = curYear - dobYear;

        int curMonth = today.get(Calendar.MONTH);
        int dobMonth = dob.get(Calendar.MONTH);
        if (dobMonth > curMonth) {
            age--;
        } else if (dobMonth == curMonth) {
            int curDay = today.get(Calendar.DAY_OF_MONTH);
            int dobDay = dob.get(Calendar.DAY_OF_MONTH);
            if (dobDay > curDay) {
                age--;
            }
        }

        return age;
    }


    public interface UserClickListener {
        void onUserClicked(UserDetails user);
    }
}
