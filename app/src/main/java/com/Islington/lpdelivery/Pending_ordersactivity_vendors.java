package com.Islington.lpdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

public class Pending_ordersactivity_vendors extends AppCompatActivity {
    private final Retrofit retrofit = APIClient.getRetrofit();
    private final APIInterface apiInterface =retrofit.create(APIInterface.class);
    List<OrdersModel> ordersModel;
    OrdersAdapter ordersAdapter;
    Sharedprefresclass sharedprefresclass;

    RecyclerView orders_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_vendor__orders);

        orders_rv = findViewById(R.id.vendor_ordersrv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        orders_rv.setLayoutManager(linearLayoutManager);
        sharedprefresclass = new Sharedprefresclass(getApplicationContext());
        String vendor_id = sharedprefresclass.getUID();
        getvendororders(vendor_id);

    }

    private void getvendororders(String vendor_id) {

        apiInterface.getvendororders(vendor_id,"0").enqueue(new Callback<OrdersModel>() {
            @Override
            public void onResponse(Call<OrdersModel> call, Response<OrdersModel> response) {


                if (response.isSuccessful()) {

                    if (response.body().getStatus().equals("1")) {

                        ordersModel = new ArrayList<>();
                        ordersAdapter = new OrdersAdapter(getApplicationContext(),response.body().getDeliverdetails());
                        orders_rv.setAdapter(ordersAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<OrdersModel> call, Throwable t) {

            }
        });
    }
}