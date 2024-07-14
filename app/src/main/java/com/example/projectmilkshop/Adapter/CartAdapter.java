package com.example.projectmilkshop.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectmilkshop.Api.CartService;
import com.example.projectmilkshop.Domain.Cart;
import com.example.projectmilkshop.Domain.CartRequest;
import com.example.projectmilkshop.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<Cart> cartItems;
    private CartService cartService;
    private String jwtToken;
    private OnCartChangeListener listener;
    public interface OnCartChangeListener {
        void onCartChanged(double totalPrice);
    }

    public CartAdapter(List<Cart> cartItems, CartService cartService, String jwtToken, OnCartChangeListener listener) {
        this.cartItems = cartItems;
        this.cartService = cartService;
        this.jwtToken = jwtToken;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        Cart cartItem = cartItems.get(position);

        holder.title.setText(cartItem.getProduct().getProductName());
        holder.fee.setText(String.valueOf(cartItem.getUnitPrice()));
        holder.quality.setText(String.valueOf(cartItem.getQuantity()));
        holder.total.setText(String.valueOf(cartItem.getUnitPrice() * cartItem.getQuantity()));

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(cartItem.getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.btnAddQuality.setOnClickListener(v -> {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            holder.total.setText(String.valueOf(cartItem.getUnitPrice() * cartItem.getQuantity()));
            notifyItemChanged(holder.getAdapterPosition());
            updateCartItem(cartItem, holder.getAdapterPosition());
            updateTotalPrice();
        });

        holder.btnMinusQuality.setOnClickListener(v -> {
            if (cartItem.getQuantity() >= 1) {
                if(cartItem.getQuantity() - 1 == 0) {
                    showRemoveConfirmationDialog(holder.itemView.getContext(), cartItem, holder.getAdapterPosition());
                } else {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                    holder.total.setText(String.valueOf(cartItem.getUnitPrice() * cartItem.getQuantity()));
                    notifyItemChanged(holder.getAdapterPosition());
                    updateCartItem(cartItem, holder.getAdapterPosition());
                    updateTotalPrice();
                }
            } else {
                Toast.makeText(holder.itemView.getContext(), "Can not minus", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showRemoveConfirmationDialog(Context context, Cart cartItem, int adapterPosition) {
        new AlertDialog.Builder(context)
                .setTitle("Confirmation")
                .setMessage("Are you sure you want to remove this item")
                .setPositiveButton("Yes", (dialog, which) -> {
                    cartItems.remove(cartItem);
                    removeCartItem(cartItem, adapterPosition);
                    notifyItemRemoved(adapterPosition);
                    updateTotalPrice();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void removeCartItem(Cart cartItem, int adapterPosition) {
        Call<Void> call = cartService.RemoveProductFromCart(cartItem.getCartId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.i("CartAdapter", "Cart item removed successfully");
                } else {
                    Log.e("CartAdapter", "Failed to remove cart item");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("CartAdapter", "API call failed", t);
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

        Call<Void> call = cartService.UpdateCartItem(cartItem.getCartId(), cartRequest);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    notifyItemChanged(position);
                    Log.i("CartAdapter", "Cart item updated successfully");
                } else {
                    Log.e("CartAdapter", "Failed to update cart item");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("CartAdapter", "API call failed", t);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    private void updateTotalPrice() {
        double total = 0;
        for (Cart cart : cartItems) {
            total += cart.getUnitPrice() * cart.getQuantity();
        }
        if (listener != null) {
            listener.onCartChanged(total);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
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

