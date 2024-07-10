package com.example.projectmilkshop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectmilkshop.Domain.CategoryDoman;
import com.example.projectmilkshop.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ArrayList<CategoryDoman> categoryDomen;

    public CategoryAdapter(ArrayList<CategoryDoman> categoryDomen) {
        this.categoryDomen = categoryDomen;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.categoryName.setText(categoryDomen.get(position).getTitle());
        String picUrl="";
        switch (position){
            case 0: {
                picUrl = "milk";
                break;
            }
            case 1: {
                picUrl = "grain_milk";
                break;
            }
        }
        int drawableResuorceId = holder.itemView.getContext().getResources()
                .getIdentifier(picUrl,"drawable",
                        holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResuorceId)
                .into(holder.categoryImage);
    }

    @Override
    public int getItemCount() {
        return categoryDomen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName;
        ImageView categoryImage;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemview){

            super(itemview);
            categoryName = itemview.findViewById(R.id.tvCategoryName);
            categoryImage = itemview.findViewById(R.id.imgCategory);
            mainLayout = itemview.findViewById(R.id.mainLayout);
        }
    }
}
