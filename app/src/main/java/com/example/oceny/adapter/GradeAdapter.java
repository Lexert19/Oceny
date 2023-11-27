package com.example.oceny.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oceny.GradeModel;
import com.example.oceny.GradeViewHolder;
import com.example.oceny.R;

import java.util.List;

public class GradeAdapter extends RecyclerView.Adapter<GradeViewHolder> {
    private List<GradeModel> gradeModels;
    private LayoutInflater layoutInflater;

    public GradeAdapter(Activity context, List<GradeModel> gradeModels) {
        this.gradeModels = gradeModels;
        this.layoutInflater = context.getLayoutInflater();
    }

    @NonNull
    @Override
    public GradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_grade, null);
        return new GradeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull GradeViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.getItemView().setTag(gradeModels.get(position));
        holder.loadTag();
    }

    @Override
    public int getItemCount() {
        return gradeModels.size();
    }

}
