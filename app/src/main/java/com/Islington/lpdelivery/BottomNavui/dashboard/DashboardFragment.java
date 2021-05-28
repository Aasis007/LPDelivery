package com.Islington.lpdelivery.BottomNavui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Islington.lpdelivery.Adapter.ProductsAdapter;
import com.Islington.lpdelivery.Model.ProductsModel;
import com.Islington.lpdelivery.Model.VendorModel;
import com.Islington.lpdelivery.R;
import com.Islington.lpdelivery.Retrofit.APIClient;
import com.Islington.lpdelivery.Retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DashboardFragment extends Fragment {
    private final Retrofit retrofit = APIClient.getRetrofit();
    private final APIInterface apiInterface =retrofit.create(APIInterface.class);
    ProductsAdapter productsAdapter;
    List<ProductsModel> productsModels;
    RecyclerView productsrv;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        productsrv = root.findViewById(R.id.vendor_rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        productsrv.setLayoutManager(gridLayoutManager);
        productsrv.setHasFixedSize(true);


        getproducts();



        return root;



    }

    private void getproducts() {
            apiInterface.getproducts().enqueue(new Callback<ProductsModel>() {
                @Override
                public void onResponse(Call<ProductsModel> call, Response<ProductsModel> response) {
                        if (response.isSuccessful()) {

                                productsModels = new ArrayList<>();
                                productsAdapter = new ProductsAdapter(getContext(), response.body().getProductsData());
                                productsrv.setAdapter(productsAdapter);


                        }



                }

                @Override
                public void onFailure(Call<ProductsModel> call, Throwable t) {

                }
            });
    }


}