package com.sujan.myapplication.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sujan.myapplication.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryVH> {
    private ArrayList<Category> categoryList = new ArrayList<>();

    public CategoryAdapter(ArrayList<Category> categoryList) {
        this.categoryList.addAll(categoryList);
    }

    @NonNull
    @Override
    public CategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_category_item, parent, false);
        return new CategoryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVH holder, int position) {
        Category obj= categoryList.get(position);
        holder.txtTitle.setText(obj.getTitle());
        holder.txtDescription.setText(obj.getDescription());

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
