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

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    ArrayList<Product> milkDomains;

    public PopularAdapter(ArrayList<Product> milkDomains) {
        this.milkDomains = milkDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {

        holder.title.setText(milkDomains.get(position).getProductName());
        holder.fee.setText(String.valueOf(milkDomains.get(position).getProductPrice()));
        int drawableResuorceId = holder.itemView.getContext().getResources()
                .getIdentifier(milkDomains.get(position).getPic(),"drawable",
                        holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResuorceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return milkDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, fee;
        ImageView pic, addBtn;

        public ViewHolder(@NonNull View itemview){

            super(itemview);
            title = itemview.findViewById(R.id.tvItemTitle);
            pic = itemview.findViewById(R.id.imgItemPic);
            fee = itemview.findViewById(R.id.tvItemFee);
            addBtn = itemview.findViewById(R.id.imgAdd);
        }
    }
}
