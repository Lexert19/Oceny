package com.example.oceny;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
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
        return new GradeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull GradeViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Grade grade = grades.get(position);
        holder.subject.setText(grade.getSubject());
        holder.grade = grade;
        holder.position = position;
        holder.setRadioButtonState();

    }

    @Override
    public int getItemCount() {
        return grades.size();
    }

    public class GradeViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener{
        private TextView subject;
        private Grade grade;
        private View itemView;
        private RadioGroup radioGroup;
        private int position;

        public GradeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            subject = itemView.findViewById(R.id.subject);
            radioGroup = itemView.findViewById(R.id.radioGroup);

            //setRadioButtonState();
            setListener();
        }

        public void setRadioButtonState(){
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(grade.getGrade()-2);
            radioButton.setChecked(grade.isChecked());
            radioButton.setChecked(true);

        }

        private void setListener(){
            radioGroup.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton radioButton = itemView.findViewById(checkedId);
            grade.setGrade(Integer.parseInt(radioButton.getText().toString()));
            grade.setChecked(!grade.isChecked());
        }

    }
}
