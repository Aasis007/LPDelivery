package com.Islington.lpdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.Islington.lpdelivery.Adapter.OrdersAdapter;
import com.Islington.lpdelivery.Adapter.VendorApprovedordersAdapter;
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

public class Vendorapprovedorders_activity extends AppCompatActivity {
    private final Retrofit retrofit = APIClient.getRetrofit();
    private final APIInterface apiInterface =retrofit.create(APIInterface.class);
    List<OrdersModel> ordersModel;
    VendorApprovedordersAdapter vendorApprovedordersAdapter;
    Sharedprefresclass sharedprefresclass;
    RecyclerView vendor_approvedordersrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendorapprovedorders_activity);


        vendor_approvedordersrv = findViewById(R.id.vendor_approvedordersrv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        vendor_approvedordersrv.setLayoutManager(linearLayoutManager);
        sharedprefresclass = new Sharedprefresclass(getApplicationContext());
        String user_id = sharedprefresclass.getUID();
        getvendorapprovedorders(user_id);
    }

    private void getvendorapprovedorders(String user_id) {

            apiInterface.getvendororders(user_id,"1").enqueue(new Callback<OrdersModel>() {
                @Override
                public void onResponse(Call<OrdersModel> call, Response<OrdersModel> response) {

                    if (response.isSuccessful()) {

                        if (response.body().getStatus().equals("1")) {

                            ordersModel = new ArrayList<>();
                            vendorApprovedordersAdapter = new VendorApprovedordersAdapter(getApplicationContext(),response.body().getDeliverdetails());
                            vendor_approvedordersrv.setAdapter(vendorApprovedordersAdapter);
                        }
                    }

                }

                @Override
                public void onFailure(Call<OrdersModel> call, Throwable t) {

                }
            });


    }
}