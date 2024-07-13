package com.example.projectmilkshop.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectmilkshop.Api.CartService;
import com.example.projectmilkshop.Domain.Cart;
import com.example.projectmilkshop.Domain.CartRequest;
import com.example.projectmilkshop.Domain.Product;
import com.example.projectmilkshop.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<Cart> cartItems;
    private CartService cartService;
    private String jwtToken;

    public CartAdapter(List<Cart> cartItems, CartService cartService, String jwtToken) {
        this.cartItems = cartItems;
        this.cartService = cartService;
        this.jwtToken = jwtToken;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        Cart cartItem = cartItems.get(holder.getAdapterPosition());
        Product product = cartItem.getProduct();
        int quantity = cartItem.getQuantity();

        holder.title.setText(product.getProductName());
        holder.fee.setText(String.valueOf(product.getProductPrice()));
        holder.quality.setText(String.valueOf(quantity));
        holder.total.setText(String.valueOf(Math.round(quantity * product.getProductPrice())));
        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(product.getPic(), "drawable",
                        holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.btnAddQuality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                notifyItemChanged(holder.getAdapterPosition());
                updateCartItem(cartItem, holder.getAdapterPosition());
            }
        });
        holder.btnMinusQuality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartItem.getQuantity() > 1) {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                    notifyItemChanged(holder.getAdapterPosition());
                    updateCartItem(cartItem, holder.getAdapterPosition());
                }
            }
        });
    }

    private void updateCartItem(Cart cartItem, int position) {
        CartRequest cartRequest = new CartRequest(
                cartItem.getProductId(),
                cartItem.getAccountId(),
                cartItem.getQuantity(),
                1
        );
        Call<Cart> call = cartService.updateCartItem(cartItem.getCartId(), cartRequest);
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                    notifyItemChanged(position);
                } else {
                    Log.e("CartAdapter", "Failed to update cart item");
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.e("CartAdapter", "API call failed", t);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvItemTitle);
            pic = itemView.findViewById(R.id.imgItemPic);
            fee = itemView.findViewById(R.id.tvItemFee);
            total = itemView.findViewById(R.id.tvItemTotal);
            btnAddQuality = itemView.findViewById(R.id.btnAddQuality);
            btnMinusQuality = itemView.findViewById(R.id.btnMinusQuality);
            quality = itemView.findViewById(R.id.tvItemQuality);
        }
    }
}
