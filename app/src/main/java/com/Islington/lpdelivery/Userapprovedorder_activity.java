package com.Islington.lpdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.Islington.lpdelivery.Adapter.OrdersAdapter;
import com.Islington.lpdelivery.Adapter.UserapprovedAdapter;
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

public class Userapprovedorder_activity extends AppCompatActivity {
    private final Retrofit retrofit = APIClient.getRetrofit();
    private final APIInterface apiInterface =retrofit.create(APIInterface.class);
    List<OrdersModel> ordersModel;
    UserapprovedAdapter userapprovedAdapter;
    Sharedprefresclass sharedprefresclass;

    RecyclerView user_approvedordersrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userapprovedorder_activity);



        user_approvedordersrv = findViewById(R.id.user_approvedordersrv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        user_approvedordersrv.setLayoutManager(linearLayoutManager);
        sharedprefresclass = new Sharedprefresclass(getApplicationContext());
        String user_id = sharedprefresclass.getUID();
        getuserapprovedorders(user_id);
    }

    private void getuserapprovedorders(String user_id) {
        apiInterface.getUserOrderspending(user_id,"1").enqueue(new Callback<OrdersModel>() {
            @Override
            public void onResponse(Call<OrdersModel> call, Response<OrdersModel> response) {

                        if (response.isSuccessful()) {

                            if (response.body().getStatus().equals("1")) {


                                ordersModel = new ArrayList<>();
                                userapprovedAdapter = new UserapprovedAdapter(getApplicationContext(),response.body().getDeliverdetails());
                                user_approvedordersrv.setAdapter(userapprovedAdapter);

                            }
                        }

            }

            @Override
            public void onFailure(Call<OrdersModel> call, Throwable t) {

            }
        });
    }
}