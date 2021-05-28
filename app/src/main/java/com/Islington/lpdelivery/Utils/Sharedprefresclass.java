package com.Islington.lpdelivery.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Sharedprefresclass {
    Context context;
    SharedPreferences sharedPreferences;

    public Sharedprefresclass(Context context) {
        this.context = context;
        sharedPreferences =context.getSharedPreferences("Userdetails",Context.MODE_PRIVATE);
    }

    public void saveUID(String uid) {

            SharedPreferences.Editor editor =sharedPreferences.edit();
            editor.putString("uid",uid);
            editor.apply();


    }

    public String getUID() {
            return sharedPreferences.getString("uid","");
    }


    public void saveaddress(String address) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("address",address);
        editor.apply();
    }


    public String getaddress() {
        return sharedPreferences.getString("address","");
    }

}
