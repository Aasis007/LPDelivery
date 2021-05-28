package com.Islington.lpdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.Islington.lpdelivery.Adapter.VendorAdapter;
import com.Islington.lpdelivery.Model.VendorModel;
import com.Islington.lpdelivery.Retrofit.APIClient;
import com.Islington.lpdelivery.Retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VendorList extends AppCompatActivity {
    private final Retrofit retrofit = APIClient.getRetrofit();
    private final APIInterface apiInterface =retrofit.create(APIInterface.class);
    RecyclerView vendorrv;
    List<VendorModel> vendorModels;
    VendorAdapter vendorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_list);

        String proname = getIntent().getStringExtra("prodname");

        vendorrv = findViewById(R.id.vendor_rv);
        vendorrv.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        vendorrv.setLayoutManager(linearLayoutManager);

        getvendorslist(proname);

    }

    private void getvendorslist(String proname) {
                apiInterface.getVendorByproduct(proname).enqueue(new Callback<VendorModel>() {
                    @Override
                    public void onResponse(Call<VendorModel> call, Response<VendorModel> response) {
                        if (response.isSuccessful()) {

                            vendorModels = new ArrayList<>();
                            assert response.body() != null;
                            vendorAdapter = new VendorAdapter(getApplicationContext(),response.body().getVendorData());
                            vendorrv.setAdapter(vendorAdapter);

                        }
                    }

                    @Override
                    public void onFailure(Call<VendorModel> call, Throwable t) {

                    }
                });

    }
}