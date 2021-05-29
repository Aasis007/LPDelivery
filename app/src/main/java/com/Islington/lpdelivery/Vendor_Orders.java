package com.Islington.lpdelivery;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.Islington.lpdelivery.Adapter.OrdersAdapter;
import com.Islington.lpdelivery.Model.OrdersModel;
import com.Islington.lpdelivery.Retrofit.APIClient;
import com.Islington.lpdelivery.Retrofit.APIInterface;
import com.Islington.lpdelivery.Utils.Sharedprefresclass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Vendor_Orders extends Fragment {

    CardView pendingorders,appreovedorders;
    ImageButton pending,approved;








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.order_vendors, container, false);

        pendingorders = view.findViewById(R.id.order_pending);
        appreovedorders = view.findViewById(R.id.order_approved);
        pending = view.findViewById(R.id.pending);
        approved = view.findViewById(R.id.approved);


        pendingorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent pendingordervendor = new Intent(getActivity(), Pending_ordersactivity_vendors.class);
                startActivity(pendingordervendor);


            }
        });


        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pendingordervendor = new Intent(getActivity(), Pending_ordersactivity_vendors.class);
                startActivity(pendingordervendor);
            }
        });



        appreovedorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent approved = new Intent(getActivity(),Vendorapprovedorders_activity.class);
                startActivity(approved);

            }
        });

        return view;





    }


}