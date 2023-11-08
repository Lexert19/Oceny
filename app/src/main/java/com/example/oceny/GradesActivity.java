package com.example.oceny;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GradesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grades_activity);

        Bundle bundle = getIntent().getExtras();
        int gradesNumber = bundle.getInt("degreesNumber");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<Grade> grades = new ArrayList<>();
        String[] subjectsList = getApplicationContext().getResources().getStringArray(R.array.subjectsList);
        for(int i=0; i<gradesNumber; i++){
            grades.add(new Grade(subjectsList[i],2));
        }
        GradeAdapter gradeAdapter = new GradeAdapter(this, grades);

        recyclerView.setAdapter(gradeAdapter);

        Bundle returnBundle = new Bundle();
        returnBundle.putInt("1",1);
        Intent intent = new Intent();
        intent.putExtras(returnBundle);
        setResult(RESULT_OK,intent);
        //finish();
    }


}
