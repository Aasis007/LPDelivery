package com.Islington.lpdelivery.BottomNavui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.Islington.lpdelivery.Adapter.NotificationsAdapter;
import com.Islington.lpdelivery.Models.NotificationsModel;
import com.Islington.lpdelivery.R;
import com.Islington.lpdelivery.Retrofit.APIClient;
import com.Islington.lpdelivery.Retrofit.APIInterface;
import com.Islington.lpdelivery.Utils.Sharedprefresclass;
import com.Islington.lpdelivery.progressDialog.ShowProgress;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NotificationsFragment extends Fragment {

    private final Retrofit retrofit = APIClient.getRetrofit();
    private final APIInterface apiInterface =retrofit.create(APIInterface.class);
    RecyclerView noti_rv;
    View view;
    NotificationsAdapter notificationsfragmentAdapter;
    NotificationsModel notificationsModels;

    Sharedprefresclass saveData;
    ShowProgress progress;
//
//

    public NotificationsFragment() {
        //required empty constructor

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notifications,container,false);

        progress = new ShowProgress(getActivity());
        saveData = new Sharedprefresclass(getActivity());

//        SessionManagement sessionManagement = new SessionManagement(getActivity());

//        String UID = sessionManagement.getSession();
//        Log.d("CPid","company"+UID);

        noti_rv = view.findViewById(R.id.notifications_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        noti_rv.setLayoutManager(linearLayoutManager);
        noti_rv.setHasFixedSize(true);
//        Log.d("cid","cid"+UID);

        try {
            progress.showProgress();
        }catch (Exception e){
            e.printStackTrace();
        }

        //get notificaitions from server
        apiInterface.getnotification(saveData.getUID()).enqueue(new Callback<NotificationsModel>() {
            @Override
            public void onResponse(@NotNull Call<NotificationsModel> call, @NotNull Response<NotificationsModel> response) {


                assert response.body() != null;
                if (response.isSuccessful()) {

                    String resp = response.body().toString();
                    Log.d("response:","notiresp"+resp);


                    notificationsfragmentAdapter = new NotificationsAdapter(getContext(),response.body().getNotification_data());

                    noti_rv.setAdapter(notificationsfragmentAdapter);


                    notificationsfragmentAdapter.notifyDataSetChanged();


//                    Log.d("notifiation","noti"+response.body().getNotification_data().get(0).getTitle());


                }
                try {
                    progress.hideProgress();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<NotificationsModel> call, Throwable t) {
                try {
                    progress.hideProgress();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        return view;

    }
}