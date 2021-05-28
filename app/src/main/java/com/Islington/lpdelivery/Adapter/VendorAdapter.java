package com.Islington.lpdelivery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Islington.lpdelivery.Model.VendorModel;
import com.Islington.lpdelivery.R;
import com.Islington.lpdelivery.Vendor_singleAtivity.Vendor_Singleactivity;

import java.util.List;

public class VendorAdapter extends RecyclerView.Adapter<VendorAdapter.ViewHolder> {

    private final Context context;
    private List<VendorModel.VendorData>vendorData;

    public VendorAdapter(Context context, List<VendorModel.VendorData> vendorData) {
        this.context = context;
        this.vendorData = vendorData;
    }

    @NonNull
    @Override
    public VendorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_vendoritems,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        VendorModel.VendorData model = vendorData.get(position);
        String prodId = model.getP_id();
        String vendorId = model.getVendor_id();
        String vendorname = model.getVendorName();
        String vendorlat = model.getLat();
        String vendorlng = model.getLon();


        holder.vendorname.setText(vendorname);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent singleprodintent = new Intent(context, Vendor_Singleactivity.class);

                singleprodintent.putExtra("pid",prodId);
                singleprodintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                singleprodintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(singleprodintent);



            }
        });
    }

    @Override
    public int getItemCount() {
        return vendorData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView vendorname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vendorname = itemView.findViewById(R.id.vendor_name);
        }
    }
}
