package com.example.projectmilkshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectmilkshop.Activity.CartActivity;
import com.example.projectmilkshop.Domain.Cart;
import com.example.projectmilkshop.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    interface OnCartChangeListener {
        void onCartChanged(double totalPrice);
    }

    private ArrayList<Cart> cartItems;
    private OnCartChangeListener listener;

    public CartAdapter(ArrayList<Cart> cartItems, OnCartChangeListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }
    public CartAdapter(ArrayList<Cart> cartItems, CartActivity cartActivity) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        Cart cart = cartItems.get(position);
        holder.title.setText(cart.getProductId());
        holder.fee.setText(String.valueOf(cart.getUnitPrice()));
        holder.quality.setText(String.valueOf(cart.getQuantity()));
        holder.total.setText(String.valueOf(cart.getUnitPrice() * cart.getQuantity()));

        int drawableResuorceId = holder.itemView.getContext().getResources()
                .getIdentifier(cart.getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResuorceId)
                .into(holder.pic);

        holder.btnAddQuality.setOnClickListener(v -> {
            cart.setQuantity(cart.getQuantity() + 1);
            holder.quality.setText(String.valueOf(cart.getQuantity()));
            holder.total.setText(String.valueOf(cart.getUnitPrice() * cart.getQuantity()));
            notifyItemChanged(position);
            updateTotalPrice();
        });

        holder.btnMinusQuality.setOnClickListener(v -> {
            if (cart.getQuantity() > 1) {
                cart.setQuantity(cart.getQuantity() - 1);
                holder.quality.setText(String.valueOf(cart.getQuantity()));
                holder.total.setText(String.valueOf(cart.getUnitPrice() * cart.getQuantity()));
                notifyItemChanged(position);
                updateTotalPrice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, fee, total, quality;
        ImageView pic, btnMinusQuality, btnAddQuality;

        public ViewHolder(@NonNull View itemview) {
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

    private void updateTotalPrice() {
        double total = 0;
        for (Cart cart : cartItems) {
            total += cart.getUnitPrice() * cart.getQuantity();
        }
        listener.onCartChanged(total);
    }
}