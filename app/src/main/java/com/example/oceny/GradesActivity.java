package com.example.oceny;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oceny.adapter.GradeAdapter;
import com.example.oceny.listener.CalculateAverage;

import java.util.ArrayList;
import java.util.List;

public class GradesActivity extends AppCompatActivity {
    private Button calculateAverageButton;
    private List<GradeModel> grades;
    private RecyclerView recyclerView;
    private GradeAdapter gradeAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grades_activity);

        findViews();
        setListeners();
        configureRecyclerView();


    }

    private void findViews(){
        this.calculateAverageButton = findViewById(R.id.calculateAverageButton);
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void setListeners(){
        calculateAverageButton.setOnClickListener(new CalculateAverage(this));
    }

    private void configureRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.grades = loadGrades();

        gradeAdapter = new GradeAdapter(this, grades);
        recyclerView.setAdapter(gradeAdapter);
    }

    private ArrayList<GradeModel> loadGrades(){
        ArrayList<GradeModel> gradeModels = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        int gradesNumber = bundle.getInt("gradesNumber");

        String[] subjectsList = getApplicationContext().getResources().getStringArray(R.array.subjectsList);
        for(int i=0; i<gradesNumber; i++){
            gradeModels.add(new GradeModel(subjectsList[i],2));
        }

        return gradeModels;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("grades", (ArrayList<GradeModel>) grades);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        grades = savedInstanceState.getParcelableArrayList("grades");
        GradeAdapter gradeAdapter = new GradeAdapter(this, grades);
        recyclerView.setAdapter(gradeAdapter);
        gradeAdapter.notifyDataSetChanged();
    }

    public void returnAverage(){
        Bundle returnBundle = new Bundle();
        double average = 0;
        for(GradeModel gradeModel : grades){
            average+= gradeModel.getGrade();
        }
        average/= grades.size();
        returnBundle.putDouble("average",average);
        Intent intent = new Intent();
        intent.putExtras(returnBundle);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void returnToParent(){
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            returnToParent();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
