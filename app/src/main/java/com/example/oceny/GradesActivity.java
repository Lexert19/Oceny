package com.example.oceny;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oceny.listener.CalculateAverageButtonListener;

import java.util.ArrayList;
import java.util.List;

public class GradesActivity extends AppCompatActivity {
    private Button calculateAverageButton;
    private List<Grade> grades;
    private RecyclerView recyclerView;
    private GradeAdapter gradeAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grades_activity);

        this.calculateAverageButton = findViewById(R.id.calculateAverageButton);
        calculateAverageButton.setOnClickListener(new CalculateAverageButtonListener(this));



        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.grades = loadGrades();

        gradeAdapter = new GradeAdapter(this, grades);
        recyclerView.setAdapter(gradeAdapter);

    }

    private ArrayList<Grade> loadGrades(){
        ArrayList<Grade> grades = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        int gradesNumber = bundle.getInt("gradesNumber");

        String[] subjectsList = getApplicationContext().getResources().getStringArray(R.array.subjectsList);
        for(int i=0; i<gradesNumber; i++){
            grades.add(new Grade(subjectsList[i],2));
        }

        return grades;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Parcelable recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelableArrayList("grades", (ArrayList<Grade>) grades);
        //outState.putParcelable("grades", (Parcelable) grades);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //savedInstanceState.getParcelable("grades");
        ArrayList<Grade> grades = savedInstanceState.getParcelableArrayList("grades");
        GradeAdapter gradeAdapter = new GradeAdapter(this, grades);
        this.grades = grades;
        recyclerView.setAdapter(gradeAdapter);
        gradeAdapter.notifyDataSetChanged();

        //Parcelable recyclerViewState = savedInstanceState.getParcelable("recycler_state");
        //recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);


    }

    public void returnAverage(){
        Bundle returnBundle = new Bundle();
        double average = 0;
        for(Grade grade : grades){
            average+=grade.getGrade();
        }
        average/=grades.size();
        returnBundle.putDouble("average",average);
        Intent intent = new Intent();
        intent.putExtras(returnBundle);
        setResult(RESULT_OK,intent);
        finish();
    }


}
