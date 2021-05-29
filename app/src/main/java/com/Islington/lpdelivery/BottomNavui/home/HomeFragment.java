package com.Islington.lpdelivery.BottomNavui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.Islington.lpdelivery.Pendingordersuser_activity;
import com.Islington.lpdelivery.R;
import com.Islington.lpdelivery.Retrofit.APIClient;
import com.Islington.lpdelivery.Retrofit.APIInterface;
import com.Islington.lpdelivery.Userapprovedorder_activity;

import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    CardView pendingordersuser,appreovedordersuser;
    ImageButton pendinguser,approveuser;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        pendingordersuser = view.findViewById(R.id.order_pending_user);
        appreovedordersuser = view.findViewById(R.id.order_approveduser);
        pendinguser = view.findViewById(R.id.pending_user);
        approveuser = view.findViewById(R.id.approved_user);



        pendingordersuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userpending = new Intent(getActivity(), Pendingordersuser_activity.class);
                startActivity(userpending);
            }
        });


        pendinguser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userpending = new Intent(getActivity(), Pendingordersuser_activity.class);
                startActivity(userpending);
            }
        });



        appreovedordersuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent userapproved = new Intent(getActivity(), Userapprovedorder_activity.class);
                startActivity(userapproved);

            }
        });


        approveuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent userapproved = new Intent(getActivity(), Userapprovedorder_activity.class);
                startActivity(userapproved);
            }
        });
        return view;
    }
}