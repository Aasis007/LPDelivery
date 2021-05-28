package com.Islington.lpdelivery.Vendor_singleAtivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.Islington.lpdelivery.Confirmorder_activity;
import com.Islington.lpdelivery.Model.ProductsModel;
import com.Islington.lpdelivery.R;
import com.Islington.lpdelivery.Retrofit.APIClient;
import com.Islington.lpdelivery.Retrofit.APIInterface;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Vendor_Singleactivity extends AppCompatActivity {
    private final Retrofit retrofit = APIClient.getRetrofit();
    private final APIInterface apiInterface =retrofit.create(APIInterface.class);
    List<ProductsModel> productsData;
    TextView prod_name,prod_price,order_type,ordertype_price,prodstock;
    RadioGroup ordertype;
    ElegantNumberButton prod_qty;
    EditText notes;
    Button submit;
    String otype;
    String proprice;
    String extraprice;
    String proqty;
    String vendor_id;
    String nots;
    String productStock;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor__singleactivity);

        prod_qty =findViewById(R.id.prod_qty);
        prod_name = findViewById(R.id.prod_name);
        prod_price = findViewById(R.id.prod_price);
        order_type = findViewById(R.id.ordr_type);
        ordertype_price = findViewById(R.id.ordetype_charge);
        prodstock = findViewById(R.id.prod_stock);
        ordertype = findViewById(R.id.order_type);



        String prodid = getIntent().getStringExtra("pid");



        fetchproddetails(prodid);
        notes = findViewById(R.id.notes);





        //ordertypes
        ordertype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                View selectedbtn = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(selectedbtn);
                switch (index) {

                    case 0:
                        order_type.setText("New Order Price");
                         extraprice = "2100";
                        ordertype_price.setText("Rs.2100");
                        otype = "1";
                        Toast.makeText(Vendor_Singleactivity.this, "type:"+otype, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        order_type.setText("Replacement Price");
                        extraprice = "0";
                        ordertype_price.setText("0");
                        otype = "2";
                        Toast.makeText(Vendor_Singleactivity.this, "type:"+otype, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        order_type.setText("Exchange Price");
                        extraprice = "300";
                        ordertype_price.setText("Rs.300");
                         otype = "3";
                        Toast.makeText(Vendor_Singleactivity.this, "type:"+otype, Toast.LENGTH_SHORT).show();
                        break;

                }

            }
        });

        submit = findViewById(R.id.submit_btn);



    }

    private void confirmorderdialog(String vendor_id,String prodid,String prodname, String proprice,String proqty,String otype, String extraprice,String nots, String productStock) {

        Intent checkoutpageintent = new Intent(getApplicationContext(), Confirmorder_activity.class);
        checkoutpageintent.putExtra("vendor_id",vendor_id);
        checkoutpageintent.putExtra("notes",nots);
        checkoutpageintent.putExtra("p_id",prodid);
        checkoutpageintent.putExtra("order_type",otype);
        checkoutpageintent.putExtra("gas_name",prodname);
        checkoutpageintent.putExtra("extra_cost",extraprice);
        checkoutpageintent.putExtra("price",proprice);
        checkoutpageintent.putExtra("qty",proqty);
        if (productStock.equals("out of Stock")) {

            Toast.makeText(this, "Cannout order product which is out of stock", Toast.LENGTH_SHORT).show();

        }
        else {
            startActivity(checkoutpageintent);
        }

    }

    private void fetchproddetails(String prodid) {

            apiInterface.getproddesc(prodid).enqueue(new Callback<ProductsModel>() {
                @Override
                public void onResponse(@NotNull Call<ProductsModel> call, @NotNull Response<ProductsModel> response) {

                    if (response.isSuccessful()) {

                        Log.d("response","response"+response.body().toString());
                        productsData = new ArrayList<>();
                        vendor_id = response.body().getProductsData().get(0).getP_id();
                        prod_name.setText(response.body().getProductsData().get(0).getName());
                        prod_price.setText(response.body().getProductsData().get(0).getPrice());
                        proprice = response.body().getProductsData().get(0).getPrice();
                        int stock = response.body().getProductsData().get(0).getStock();


                        String prodname = response.body().getProductsData().get(0).getName();
                       if (stock <= 0) {

                           prodstock.setText("Out of Stock");
                       }

                       else {
                           prodstock.setText("Yes");


                       }

                        productStock = prodstock.getText().toString();
                        submit.setOnClickListener(view16 -> {
                            proqty = prod_qty.getNumber();
                            nots = notes.getText().toString().trim();

                            confirmorderdialog(vendor_id,prodid,prodname,proprice,proqty,otype,extraprice,nots,productStock);
                        });


                    }

                }

                @Override
                public void onFailure(@NotNull Call<ProductsModel> call, @NotNull Throwable t) {

                }
            });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}