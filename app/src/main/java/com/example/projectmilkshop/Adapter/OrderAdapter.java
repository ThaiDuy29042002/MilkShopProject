package com.example.projectmilkshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmilkshop.Domain.Order;
import com.example.projectmilkshop.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<Order> orders;

    public OrderAdapter(List<Order> orders) {
        this.orders = orders;
    }


    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.tvOrderId.setText(String.valueOf(order.getOrderId()));
        holder.tvTotalPrice.setText(String.valueOf(order.getTotalPrice()));
        holder.tvOrderDate.setText(order.getOrderDate());
        if(order.getStatus() == 1)  {
            holder.tvStatus.setText("Paid");
            holder.tvStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.green));
        } else if(order.getStatus() == 2) {
            holder.tvStatus.setText("Failed");
            holder.tvStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.textColorF));
        } else {
            holder.tvStatus.setText("Pending");
            holder.tvStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.yellow));
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId;
        TextView tvTotalPrice;
        TextView tvOrderDate;
        TextView tvStatus;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
            tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
