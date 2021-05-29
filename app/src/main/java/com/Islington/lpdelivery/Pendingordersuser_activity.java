package com.Islington.lpdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.Islington.lpdelivery.Adapter.OrdersAdapter;
import com.Islington.lpdelivery.Adapter.UserpendingordersAdapter;
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

public class Pendingordersuser_activity extends AppCompatActivity {
    private final Retrofit retrofit = APIClient.getRetrofit();
    private final APIInterface apiInterface =retrofit.create(APIInterface.class);
    List<OrdersModel> ordersModel;
    Sharedprefresclass sharedprefresclass;
    UserpendingordersAdapter userpendingordersAdapter;
    RecyclerView user_pendingordersrv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendingordersuser_activity);

        user_pendingordersrv = findViewById(R.id.user_pendingordersrv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        user_pendingordersrv.setLayoutManager(linearLayoutManager);
        sharedprefresclass = new Sharedprefresclass(getApplicationContext());
        String user_id = sharedprefresclass.getUID();
        getuserpendingorders(user_id);
    }

    private void getuserpendingorders(String user_id) {

        apiInterface.getUserOrderspending(user_id,"0").enqueue(new Callback<OrdersModel>() {
            @Override
            public void onResponse(Call<OrdersModel> call, Response<OrdersModel> response) {


                if (response.isSuccessful()) {

                    if (response.body().getStatus().equals("1")) {

                        ordersModel = new ArrayList<>();
                        userpendingordersAdapter = new UserpendingordersAdapter(getApplicationContext(),response.body().getDeliverdetails());
                        user_pendingordersrv.setAdapter(userpendingordersAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<OrdersModel> call, Throwable t) {

            }
        });
    }
}