package com.example.oceny;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GradeViewHolder  extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener{
    private TextView subject;
    private GradeModel gradeModel;
    private View itemView;
    private RadioGroup radioGroup;

    public GradeViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;

        subject = itemView.findViewById(R.id.subject);
        radioGroup = itemView.findViewById(R.id.radioGroup);
    }

    public void loadTag(){
        gradeModel = (GradeModel)itemView.getTag();
        subject.setText(gradeModel.getSubject());

        setRadioButtonState();
        setListener();
    }

    public void setRadioButtonState(){
        RadioButton radioButton = (RadioButton) radioGroup.getChildAt(gradeModel.getGrade()-2);
        radioButton.setChecked(true);
    }

    private void setListener(){
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton = itemView.findViewById(checkedId);

        gradeModel.setGrade(Integer.parseInt(radioButton.getText().toString()));
    }

    public View getItemView() {
        return itemView;
    }

}
