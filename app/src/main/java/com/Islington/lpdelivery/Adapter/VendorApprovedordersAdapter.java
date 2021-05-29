package com.Islington.lpdelivery.Adapter;

import android.content.Context;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Islington.lpdelivery.Model.OrdersModel;
import com.Islington.lpdelivery.Model.RequestOrderResponse;
import com.Islington.lpdelivery.R;
import com.Islington.lpdelivery.Retrofit.APIClient;
import com.Islington.lpdelivery.Retrofit.APIInterface;
import com.Islington.lpdelivery.Utils.Sharedprefresclass;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VendorApprovedordersAdapter extends RecyclerView.Adapter<VendorApprovedordersAdapter.ViewHolder> {
    private final Retrofit retrofit = APIClient.getRetrofit();
    private final APIInterface apiInterface =retrofit.create(APIInterface.class);


    private Context context;
    private List<OrdersModel.Deliverdetails> ordersModel;

    private Sharedprefresclass sharedprefresclass;
    public VendorApprovedordersAdapter(Context context, List<OrdersModel.Deliverdetails> ordersModel) {
        this.context = context;
        this.ordersModel = ordersModel;
        this.sharedprefresclass = sharedprefresclass;
    }

    @NonNull
    @Override
    public VendorApprovedordersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendorapprovedorders_items,parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VendorApprovedordersAdapter.ViewHolder holder, int position) {


        OrdersModel.Deliverdetails model = ordersModel.get(position);
        String o_date = model.getOrder_date();
        String o_id = model.getOrder_id();
        String p_name = model.getpName();
        String o_type = model.getOrder_type();
        String o_qty = model.getQty();
        String tot = model.getTotal_cost();
        String o_notes = model.getNotes().trim();
        String cus_name =  model.getcName().trim();
        String cus_add = model.getDelivery_address().trim();
        String cus_phone = model.getcPhone();
        String cus_id = model.getCustomer_id();

        sharedprefresclass = new Sharedprefresclass(context);

        String vendor_id = sharedprefresclass.getUID();

        holder.order_date.setText(o_date);
        holder.orer_id.setText(o_id);
        holder.prod_name.setText(p_name);
        holder.order_type.setText(o_type);
        holder.order_qty.setText(o_qty);
        holder.total.setText(tot);
        holder.notes.setText(o_notes);
        holder.c_name.setText(cus_name);
        holder.c_address.setText(cus_add);
        holder.c_number.setText(cus_phone);

        holder.approve_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestBody requestBody = new MultipartBody.Builder()
                                            .setType(MultipartBody.FORM)
                                            .addFormDataPart("order_id",o_id)
                                            .addFormDataPart("delivery_address",cus_add)
                                            .build();

                apiInterface.completedelivery(requestBody).enqueue(new Callback<RequestOrderResponse>() {
                    @Override
                    public void onResponse(Call<RequestOrderResponse> call, Response<RequestOrderResponse> response) {

                        if (response.isSuccessful()) {


                            if (response.body().getStatus().equals("1"))  {

                                holder.approve_btn.setText("Completed");
                                holder.approve_btn.setEnabled(false);
                                Toast.makeText(context, "Delivery Completed", Toast.LENGTH_SHORT).show();


                                deleteorder();


                            }
                        }

                    }

                    private void deleteorder() {

                        RequestBody requestBody1 = new MultipartBody.Builder()
                                                    .setType(MultipartBody.FORM)
                                                    .addFormDataPart("customer_id",cus_id)
                                                    .addFormDataPart("vendor_id",vendor_id)
                                                    .addFormDataPart("order_id",o_id)
                                                    .build();

                        apiInterface.deleteorder(requestBody1).enqueue(new Callback<RequestOrderResponse>() {
                            @Override
                            public void onResponse(Call<RequestOrderResponse> call, Response<RequestOrderResponse> response) {

                                Toast.makeText(context, "Delivery Completed", Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onFailure(Call<RequestOrderResponse> call, Throwable t) {

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<RequestOrderResponse> call, Throwable t) {

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return ordersModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView order_date,orer_id,prod_name,order_type,order_qty,total,notes,c_name,c_address,c_number;
        Button approve_btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            order_date = itemView.findViewById(R.id.order_date);
            orer_id = itemView.findViewById(R.id.order_id);
            prod_name = itemView.findViewById(R.id.prod_name);
            order_type = itemView.findViewById(R.id.order_type);
            order_qty = itemView.findViewById(R.id.order_qty);
            total = itemView.findViewById(R.id.total);
            notes = itemView.findViewById(R.id.notes);
            c_name = itemView.findViewById(R.id.c_name);
            c_address = itemView.findViewById(R.id.c_address);
            c_number = itemView.findViewById(R.id.c_phone);
            approve_btn = itemView.findViewById(R.id.completedelivery_btn);
        }
    }
}
