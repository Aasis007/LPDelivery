package com.Islington.lpdelivery.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Islington.lpdelivery.MainActivity;
import com.Islington.lpdelivery.Model.LoginResponseModel;
import com.Islington.lpdelivery.R;
import com.Islington.lpdelivery.Retrofit.APIClient;
import com.Islington.lpdelivery.Retrofit.APIInterface;
import com.Islington.lpdelivery.Signup.Activity_register;
import com.Islington.lpdelivery.Utils.Sharedprefresclass;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login_activity extends AppCompatActivity {
    private final Retrofit retrofit = APIClient.getRetrofit();
    private final APIInterface apiInterface =retrofit.create(APIInterface.class);
    Sharedprefresclass sharedprefresclass;
    List<LoginResponseModel> loginResponseModel;
    TextInputEditText uname,upassword;
    Button signin;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        uname = findViewById(R.id.u_email);
        upassword = findViewById(R.id.u_password);
        signin = findViewById(R.id.btn_signin);
        signup = findViewById(R.id.txt_signup);


        sharedprefresclass = new Sharedprefresclass(getApplicationContext());

        signin.setOnClickListener(view -> {

            prepardataforserver(uname,upassword);


        });

        signup.setOnClickListener(view -> {
            Intent registerintent = new Intent(getApplicationContext(), Activity_register.class);
            startActivity(registerintent);
        });

    }

    private void prepardataforserver(TextInputEditText uname, TextInputEditText upassword) {

                String loginname = uname.getText().toString().trim();
                String loginpasswd = upassword.getText().toString().trim();

        RequestBody requestBody = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("email_id",loginname)
                                .addFormDataPart("password",loginpasswd)
                                .build();

        apiInterface.login(requestBody).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful()) {

                        if (response.body().getStatus().equals("1")) {
                            loginResponseModel = new ArrayList<>();
                            String cid = response.body().getUserdata().getId();
                            String address = response.body().getUserdata().getAddress();
                           sharedprefresclass.saveaddress(address);
                            sharedprefresclass.saveUID(cid);
                            Log.d("cid","id"+cid);
                            Toast.makeText(getApplicationContext(), "Login Succssful"+cid, Toast.LENGTH_SHORT).show();
                            Intent mainactivityintent = new Intent(Login_activity.this,MainActivity.class);

                            startActivity(mainactivityintent);

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                        }




                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to Login"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}