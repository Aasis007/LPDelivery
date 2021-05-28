package com.Islington.lpdelivery.Signup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.Manifest.permission;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.Islington.lpdelivery.Login.Login_activity;
import com.Islington.lpdelivery.Model.LoginResponseModel;
import com.Islington.lpdelivery.Model.Registerresponse;
import com.Islington.lpdelivery.R;
import com.Islington.lpdelivery.Retrofit.APIClient;
import com.Islington.lpdelivery.Retrofit.APIInterface;
import com.Islington.lpdelivery.Utils.Sharedprefresclass;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileNotFoundException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Activity_register extends AppCompatActivity {
    private final Retrofit retrofit = APIClient.getRetrofit();
    private final APIInterface apiInterface =retrofit.create(APIInterface.class);
    FusedLocationProviderClient fusedLocationProviderClient;
    TextInputLayout fname, femail, fpassword, fconpassword, fphone,faddress;
    TextInputEditText uname, uemail, upasswprd, confirmpass, uphone,uaddres;
    RadioGroup usertype;
    RadioButton selected_btn;
    Button signup;
    ImageButton pickimage;
    TextView signin;
    LinearLayout img_picker;
    String latitude = null;
    String longitude = null;
    String usrtype;
    File file;
    String userType = "0";
    Sharedprefresclass saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fname = findViewById(R.id.container_name);
        femail = findViewById(R.id.container_email);
        fpassword = findViewById(R.id.container_password);
        fconpassword = findViewById(R.id.container_con_pwd);
        faddress = findViewById(R.id.container_address);
        fphone = findViewById(R.id.container_number);
        uname = findViewById(R.id.u_name);
        uemail = findViewById(R.id.u_email);
        upasswprd = findViewById(R.id.u_password);
        confirmpass = findViewById(R.id.password);
        uphone = findViewById(R.id.u_number);
        uaddres = findViewById(R.id.u_address);
        signup = findViewById(R.id.btn_signup);
        signin = findViewById(R.id.txt_signin);
        usertype = findViewById(R.id.user_type);

        saveData = new Sharedprefresclass(getApplicationContext());



        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //current user location
        getlocation();

        //logic for suertype
        usertype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View selected_btn = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(selected_btn);
                switch (index) {
                    case 0:
                        usrtype ="2";
//                        img_picker.setVisibility(View.VISIBLE);
                        break;

                    case 1:
                        usrtype = "1";
//                       img_picker.setVisibility(View.GONE);
                }


            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginintent = new Intent(getApplicationContext(), Login_activity.class);
                startActivity(loginintent);
            }
        });

        //Image picker
//        pickimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                chooseimage();
//            }
//        });

        signup.setOnClickListener(view -> {

            String username = uname.getText().toString().trim();
            String useremail = uemail.getText().toString().trim();
            String userpwd = upasswprd.getText().toString().trim();
            String conpwd = confirmpass.getText().toString().trim();
            String userphone = uphone.getText().toString().trim();
            String useraddress = uaddres.getText().toString().trim();



            validate(username, useremail, userpwd, conpwd, userphone,useraddress);


        });


    }

    private void preparedataforregister( String username, String uemail, String upasswprd, String userphone,String userad, String latitude, String longitude,String usrtype) {


                    RequestBody requestBody = new MultipartBody.Builder()
                                                .setType(MultipartBody.FORM)
                                                .addFormDataPart("name",username)
                                                .addFormDataPart("email_id",uemail)
                                                .addFormDataPart("password",upasswprd)
                                                .addFormDataPart("phone",userphone)
                                                .addFormDataPart("address",userad)
                                                .addFormDataPart("lat",latitude)
                                                .addFormDataPart("lon",longitude)
                                                .addFormDataPart("user_type",usrtype)
//                                                .addFormDataPart("image",file.getName(), RequestBody.create(MediaType.parse("image/png"),file))
                                                .build();

                    apiInterface.register(requestBody).enqueue(new Callback<LoginResponseModel>() {
                        @Override
                        public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

                            if (response.isSuccessful()) {

                                if (response.body().getStatus().equals("1")) {

                                    saveData.saveaddress(userad);
                                    saveData.saveEmail(uemail);
                                    saveData.saveUID(response.body().getUserdata().getId());
                                    saveData.saveUserType(usrtype.equals("1") ? "customer" : "vendor");
                                    Toast.makeText(getApplicationContext(), "Registered Sucesfully", Toast.LENGTH_SHORT).show();
                                }

                                else {
                                    Toast.makeText(getApplicationContext(), "Failed to Register", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                        }
                    });



    }


    public  void getlocation() {

        //get location
        if (ContextCompat.checkSelfPermission(getApplicationContext(), permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Activity_register.this, new String[]{permission.ACCESS_FINE_LOCATION}, 3000);

        } else {
            if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(Activity_register.this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if (location != null) {

                        final double lat = location.getLatitude();
                        final double lng = location.getLongitude();

                        latitude = String.valueOf(lat);
                        longitude = String.valueOf(lng);

                        Toast.makeText(Activity_register.this, "coordinates"+lat+lng, Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }


    }


    public void validate(String username, String useremail, String userpwd, String conpwd, String userphone, String useraddss) {

        boolean isValid = true;
        if (username.isEmpty()) {
            fname.setError("Field cannotbeempty");
            isValid = false;
        }
        else {
            fname.setErrorEnabled(false);
        }

        if (useraddss.isEmpty()) {
            faddress.setError("Field cannotbeempty");
            isValid = false;
        }
        else {
            faddress.setErrorEnabled(false);
        }

        if (useremail.isEmpty()) {
            femail.setError("email cannot be empty");
            isValid = false;
        }
        else
            femail.setErrorEnabled(false);


        if (userpwd.isEmpty()) {
            fpassword.setError("cannot be empty");
            isValid = false;
        }
        else fpassword.setErrorEnabled(false);


         if (!conpwd.equals(userpwd)) {

            isValid = false;
            fconpassword.requestFocus();
            fconpassword.setErrorEnabled(true);
            fconpassword.setError("Passwords do not match");
        }

            fconpassword.setErrorEnabled(false);


        if (userphone.isEmpty()) {
            fphone.setError("cannot be empty");
            isValid = false;
        }
        else
            fphone.setErrorEnabled(false);

        if (usertype.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "please select one user type", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (isValid) {

            preparedataforregister(username,useremail,userpwd,userphone,useraddss,latitude,longitude,usrtype);


//            Toast.makeText(this, "Registered succsfully", Toast.LENGTH_SHORT).show();
        }




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 1000:
                if (resultCode== RESULT_OK) {

                    Uri selectedimage= data.getData();
                    pickimage.setImageURI(selectedimage);
                    File f = new File(selectedimage.getPath());
                    Toast.makeText(this, "filepath:"+f.toString(), Toast.LENGTH_SHORT).show();
                    f = file;
                }
                break;






        }
    }
    //image picker
//    private void chooseimage() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_register.this);
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogview = inflater.inflate(R.layout.image_pickerlayout,null);
//        builder.setCancelable(true);
//        builder.setView(dialogview);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//
//        ImageView gallerybtm = dialogview.findViewById(R.id.gallery_btn);
//
//
//
//
//
//        gallerybtm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                selectfromgallery();
//                alertDialog.cancel();
//            }
//
//            private void selectfromgallery() {
//
//                    Intent galleryintent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(galleryintent,1000);
//            }
//
//        });
//
//
//    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);



        if (requestCode == 3000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getlocation();

        }
        else {
            Toast.makeText(this, "Permission Denied by user", Toast.LENGTH_SHORT).show();
        }



    }



}