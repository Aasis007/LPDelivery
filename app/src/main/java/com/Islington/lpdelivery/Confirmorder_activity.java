package com.Islington.lpdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Islington.lpdelivery.Model.RequestOrderResponse;
import com.Islington.lpdelivery.Retrofit.APIClient;
import com.Islington.lpdelivery.Retrofit.APIInterface;
import com.Islington.lpdelivery.Utils.Sharedprefresclass;
import com.google.android.material.badge.BadgeDrawable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Confirmorder_activity extends AppCompatActivity {
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    private final Retrofit retrofit = APIClient.getRetrofit();
    private final APIInterface apiInterface =retrofit.create(APIInterface.class);
    Sharedprefresclass sharedprefresclass;
    TextView productname,quantity,prodprice,ecost,deliverycharge,totalcost,delivadress;
    Button sendbtn;
    List<RequestOrderResponse> requestOrderResponse;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmorder_activity);


        sharedprefresclass = new Sharedprefresclass(getApplicationContext());
        //orderdate
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String cdate = dateFormat.format(new Date());
        //customerid
        String customer_id = sharedprefresclass.getUID();
        Toast.makeText(this, "cid"+customer_id, Toast.LENGTH_SHORT).show();
        //notes
        String notes = getIntent().getStringExtra("notes");
        //productID
        String p_id = getIntent().getStringExtra("p_id");
        //ordertype
        String order_type = getIntent().getStringExtra("order_type");
        //Productname
        String prodname = getIntent().getStringExtra("gas_name");
        //extracost
        String extra_cost = getIntent().getStringExtra("extra_cost");
        //price
        String price = getIntent().getStringExtra("price");
        //qty
        String qty = getIntent().getStringExtra("qty");
        Toast.makeText(this, "qty"+qty, Toast.LENGTH_SHORT).show();
        //total cost
        int total = ((Integer.parseInt(price) * Integer.parseInt(qty)) +Integer.parseInt(extra_cost) + 300);
        String totalcosting = String.valueOf(total);
        Toast.makeText(this, "total"+total, Toast.LENGTH_SHORT).show();
        //address
        String address = sharedprefresclass.getaddress();
        //vendor ID
        String vendor_id =  getIntent().getStringExtra("vendor_id");



        productname = findViewById(R.id.prodname);
        quantity = findViewById(R.id.qty);
        prodprice = findViewById(R.id.prodprice);
        ecost = findViewById(R.id.extracharge);
        deliverycharge = findViewById(R.id.deliverycharge);
        totalcost = findViewById(R.id.totalcost);
        delivadress = findViewById(R.id.address);
        sendbtn = findViewById(R.id.sendorder);


        productname.setText(prodname);
        quantity.setText(qty);
        prodprice.setText(price);
        ecost.setText(extra_cost);
        deliverycharge.setText("Rs.300");
        totalcost.setText(totalcosting);
        delivadress.setText(address);


        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //prepare data for sending
                RequestBody requestBody = new MultipartBody.Builder()
                                            .setType(MultipartBody.FORM)
                                            .addFormDataPart("order_date",cdate)
                                            .addFormDataPart("customer_id",customer_id)
                                            .addFormDataPart("notes",notes)
                                            .addFormDataPart("p_id",p_id)
                                            .addFormDataPart("order_type",order_type)
                                            .addFormDataPart("gas_name",prodname)
                                            .addFormDataPart("extra_cost",extra_cost)
                                            .addFormDataPart("price",price)
                                            .addFormDataPart("qty",qty)
                                            .addFormDataPart("total_cost", String.valueOf(total))
                                            .addFormDataPart("address",address)
                                            .addFormDataPart("vendor_id",vendor_id)
                                            .build();



                apiInterface.sendorder(requestBody).enqueue(new Callback<RequestOrderResponse>() {
                    @Override
                    public void onResponse(Call<RequestOrderResponse> call, Response<RequestOrderResponse> response) {
                        System.out.println(response.body().toString());
                        if (response.isSuccessful()) {

                            if (response.body().getStatus().equals("1")) {


                                Toast.makeText(getApplication(), "Thank You Your Order Has been placed", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                        }

                        else  {
                            Toast.makeText(getApplication(), "Sorry Couldnot request your order", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<RequestOrderResponse> call, Throwable t) {

                    }
                });

            }
        });




    }
}