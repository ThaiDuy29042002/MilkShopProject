package com.example.projectmilkshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectmilkshop.Domain.Product;
import com.example.projectmilkshop.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<Product> milkDomains;

    public ProductAdapter(ArrayList<Product> milkDomains) {
        this.milkDomains = milkDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_product, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        holder.title.setText(milkDomains.get(position).getProductName());
        holder.fee.setText(String.valueOf(milkDomains.get(position).getProductPrice()));
        holder.quality.setText(String.valueOf(milkDomains.get(position).getCapacity()));
        holder.total.setText(String.valueOf(Math.round(milkDomains.get(position).getCapacity()*milkDomains.get(position).getProductPrice())));
        int drawableResuorceId = holder.itemView.getContext().getResources()
                .getIdentifier(milkDomains.get(position).getPic(),"drawable",
                        holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResuorceId)
                .into(holder.pic);

        holder.btnAddQuality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.btnMinusQuality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return milkDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, fee, total, quality;
        ImageView pic, btnMinusQuality, btnAddQuality;

        public ViewHolder(@NonNull View itemview){

            super(itemview);
            title = itemview.findViewById(R.id.tvItemTitle);
            pic = itemview.findViewById(R.id.imgItemPic);
            fee = itemview.findViewById(R.id.tvItemFee);
            total = itemview.findViewById(R.id.tvItemTotal);
            btnAddQuality = itemview.findViewById(R.id.btnAddQuality);
            btnMinusQuality = itemview.findViewById(R.id.btnMinusQuality);
            quality = itemview.findViewById(R.id.tvItemQuality);
        }
    }
}
