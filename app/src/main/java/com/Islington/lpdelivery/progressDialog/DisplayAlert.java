package com.Islington.lpdelivery.progressDialog;

import android.app.AlertDialog;
import android.content.Context;

import com.Islington.lpdelivery.Retrofit.APIClient;

public class DisplayAlert {
    Context context;
    AlertDialog alert;

    public DisplayAlert(Context context) {
        this.context = context;
    }

    public void showAlertSingle(String message){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle(APIClient.AppName);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                (dialog, id) -> dialog.cancel());


        AlertDialog alert = builder1.create();
        alert.show();
    }

    public void showAlertDouble(String message){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle(APIClient.AppName);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                (dialog, id) -> dialog.cancel());

        builder1.setNegativeButton(
                "CANCEL",
                (dialog, id) -> dialog.cancel());

        AlertDialog alert = builder1.create();
        alert.show();
    }

    public void hideAlert(){
        if (alert.isShowing()){
            alert.dismiss();
        }
    }
}
