package com.sujan.myapplication.category;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryVH> {
    private ArrayList<Category> categoryList= new ArrayList<>();
    public CategoryAdapter(ArrayList<Category> categoryList){
        this.categoryList.addAll(categoryList);
    }
    @NonNull
    @Override
    public CategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
