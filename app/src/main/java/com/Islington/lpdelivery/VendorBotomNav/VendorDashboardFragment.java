package com.Islington.lpdelivery.VendorBotomNav;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Islington.lpdelivery.Adapter.ProductsAdapter;
import com.Islington.lpdelivery.Addproduct_activity;
import com.Islington.lpdelivery.Model.ProductsModel;
import com.Islington.lpdelivery.Model.VendorModel;
import com.Islington.lpdelivery.R;
import com.Islington.lpdelivery.Retrofit.APIClient;
import com.Islington.lpdelivery.Retrofit.APIInterface;
import com.Islington.lpdelivery.Utils.Sharedprefresclass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class VendorDashboardFragment extends Fragment {
    Sharedprefresclass sharedprefresclass;
    private final Retrofit retrofit = APIClient.getRetrofit();
    private final APIInterface apiInterface =retrofit.create(APIInterface.class);
    ProductsAdapter productsAdapter;
    List<ProductsModel> productsModels;
    RecyclerView vendoritems_rv;
    FloatingActionButton addbtn;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       
        View view = inflater.inflate(R.layout.fragment_vendor_dashboard, container, false);

        sharedprefresclass = new Sharedprefresclass(getContext());

        String vendor_id = sharedprefresclass.getUID();
        
        vendoritems_rv = view.findViewById(R.id.vendor_productsrv);
        addbtn = view.findViewById(R.id.addproduct_btn);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        vendoritems_rv.setLayoutManager(gridLayoutManager);
        vendoritems_rv.setHasFixedSize(true);
        
        
        getvendorproducts(vendor_id);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent addprodintent = new Intent(getActivity(), Addproduct_activity.class);
                startActivity(addprodintent);

            }
        });
        return view;
        
        
    }

    private void getvendorproducts(String vendor_id) {

                apiInterface.getvendorproducts(vendor_id).enqueue(new Callback<ProductsModel>() {
                    @Override
                    public void onResponse(Call<ProductsModel> call, Response<ProductsModel> response) {

                        if (response.isSuccessful()) {

                            productsModels = new ArrayList<>();
                            productsAdapter = new ProductsAdapter(getContext(),response.body().getProductsData());
                            vendoritems_rv.setAdapter(productsAdapter);


                        }
                    }

                    @Override
                    public void onFailure(Call<ProductsModel> call, Throwable t) {

                    }
                });

    }
}