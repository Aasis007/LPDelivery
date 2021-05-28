package com.Islington.lpdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.Islington.lpdelivery.Model.RequestOrderResponse;
import com.Islington.lpdelivery.Retrofit.APIClient;
import com.Islington.lpdelivery.Retrofit.APIInterface;
import com.Islington.lpdelivery.Utils.Sharedprefresclass;
import com.Islington.lpdelivery.VendorBotomNav.VendorDashboardFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Addproduct_activity extends AppCompatActivity {
    private final Retrofit retrofit = APIClient.getRetrofit();
    private final APIInterface apiInterface =retrofit.create(APIInterface.class);
    TextInputLayout fpname, fpprice, fpstock;
    TextInputEditText pname,pprice,pstock;
    Button submitbtn;
    Sharedprefresclass sharedprefresclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct_activity);


        fpname = findViewById(R.id.container_pname);
        fpprice = findViewById(R.id.container_pprice);
        fpstock = findViewById(R.id.container_stock);

        pname =findViewById(R.id.p_name);
        pprice = findViewById(R.id.p_price);
        pstock = findViewById(R.id.p_stock);



        sharedprefresclass = new Sharedprefresclass(getApplicationContext());

        submitbtn = findViewById(R.id.btn_submit);

        submitbtn.setOnClickListener(view -> {

            String prodname = pname.getText().toString().trim();
            String prodprice = pprice.getText().toString();
            String prodstock = pstock.getText().toString();

            validate(prodname,prodprice,prodstock);

            preparedataforadd(prodname,prodprice,prodstock);


        });

    }

    private void validate(String prodname, String prodprice, String prodstock) {
        boolean isValid = true;
        if (prodname.isEmpty()) {

            fpname.setError("Field cannot be mpty");
            isValid = false;
        }
        else {
            fpname.setErrorEnabled(false);
        }

        if (prodprice.isEmpty()) {

            fpprice.setError("Please enter price");
            isValid = false;
        }
        else {
            fpprice.setErrorEnabled(false);
        }

        if (prodstock.isEmpty()) {

            fpstock.setError("Please Enter Stock");
            isValid = false;

        }
        else {

            fpstock.setErrorEnabled(false);
        }


        if (isValid) {
            Toast.makeText(getApplicationContext(), "Product Added Sucessfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void preparedataforadd(String prodname, String prodprice, String prodstock) {

        String vendor_id = sharedprefresclass.getUID();

        RequestBody requestBody = new MultipartBody.Builder()
                                    .setType(MultipartBody.FORM)
                                    .addFormDataPart("name",prodname)
                                    .addFormDataPart("price",prodprice)
                                    .addFormDataPart("stock",prodstock)
                                    .addFormDataPart("vendor_id",vendor_id)
                                    .build();


            apiInterface.addproduct(requestBody).enqueue(new Callback<RequestOrderResponse>() {
                @Override
                public void onResponse(Call<RequestOrderResponse> call, Response<RequestOrderResponse> response) {

                    if (response.isSuccessful()) {

                        if (response.body().getStatus().equals("1")) {


                            Toast.makeText(getApplicationContext(), "Product Added", Toast.LENGTH_SHORT).show();

                            Intent dashboardintent = new Intent(getApplicationContext(), VendorDashboardFragment.class);
                            startActivity(dashboardintent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Failed to Add Product", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

                @Override
                public void onFailure(Call<RequestOrderResponse> call, Throwable t) {

                }
            });


    }
}