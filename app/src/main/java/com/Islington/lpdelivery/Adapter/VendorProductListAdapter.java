package com.Islington.lpdelivery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Islington.lpdelivery.Model.ProductsModel;
import com.Islington.lpdelivery.R;
import com.Islington.lpdelivery.VendorList;
import com.Islington.lpdelivery.Vendor_singleAtivity.Vendor_Singleactivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VendorProductListAdapter extends RecyclerView.Adapter<VendorProductListAdapter.ViewHolder> {
    private Context context;
    private List<ProductsModel.ProductsData> productsModels;


    public VendorProductListAdapter(Context context, List<ProductsModel.ProductsData> productsModels) {
        this.context = context;
        this.productsModels = productsModels;
    }

    @NonNull
    @Override
    public VendorProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_productitems,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductsModel.ProductsData model = productsModels.get(position);
        String vendorimage = model.getImage();
        String productname = model.getName();

        holder.vendor_name.setText(productname);
        Picasso.get().load(R.drawable.gas).into(holder.vendor_img);



    }

    @Override
    public int getItemCount() {
        try {
            return productsModels.size();
        }
        catch (Exception e) {


        }
        return 0;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView vendor_img;
        TextView vendor_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vendor_img = itemView.findViewById(R.id.vendorimg);
            vendor_name = itemView.findViewById(R.id.vendorname);
        }
    }
}
