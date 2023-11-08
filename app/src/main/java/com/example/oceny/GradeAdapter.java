package com.example.oceny;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.GradeViewHolder> {
    private List<Grade> grades;
    private LayoutInflater layoutInflater;

    public GradeAdapter(Activity context, List<Grade> grades) {
        this.grades = grades;
        this.layoutInflater = context.getLayoutInflater();
    }

    @NonNull
    @Override
    public GradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_grade, null);
        //View view = l.from(parent.getContext()).inflate(R.layout.item_grade, parent, false);
        return new GradeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeViewHolder holder, int position) {
        Grade grade = grades.get(position);
        holder.subject.setText(grade.getSubject());
    }

    @Override
    public int getItemCount() {
        return grades.size();
    }

    public class GradeViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener{
        public TextView subject;
        private int grade;

        public GradeViewHolder(@NonNull View itemView) {
            super(itemView);

            subject = itemView.findViewById(R.id.subject);
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

        }
    }
}
