package com.sujan.myapplication.category;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sujan.myapplication.R;

public class CategoryVH extends RecyclerView.ViewHolder {
    public TextView txtTitle;
    public TextView txtDescription;

    public CategoryVH( View itemView) {
        super(itemView);
        txtTitle=itemView.findViewById(R.id.txtTitle);
        txtDescription=itemView.findViewById(R.id.txtDescription);
    }
}
