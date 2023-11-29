package com.example.oceny;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oceny.listener.CreateGradesActivity;
import com.example.oceny.listener.EndApplication;
import com.example.oceny.listener.IsGradesButtonActive;
import com.example.oceny.listener.IsGradesNumberInputCorrect;
import com.example.oceny.listener.IsNameCorrect;
import com.example.oceny.listener.IsSurnameInputCorrect;

public class MainActivity extends AppCompatActivity {
    private EditText nameInput;
    private EditText surnameInput;
    private EditText gradesNumberInput;
    private Button gradesButton;
    private TextView averageTextView;
    private boolean passed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setListeners();
    }

    public void findViews(){
        nameInput = findViewById(R.id.nameInput);
        surnameInput = findViewById(R.id.surnameInput);
        gradesNumberInput = findViewById(R.id.gradesNumberInput);
        gradesButton = findViewById(R.id.gradesButton);
        averageTextView = findViewById(R.id.average);
    }

    public void setListeners(){
        IsNameCorrect isNameCorrect = new IsNameCorrect(this);
        nameInput.setOnFocusChangeListener(isNameCorrect);

        IsGradesButtonActive isGradesButtonActive = new IsGradesButtonActive(this);
        nameInput.addTextChangedListener(isGradesButtonActive);
        surnameInput.addTextChangedListener(isGradesButtonActive);
        gradesNumberInput.addTextChangedListener(isGradesButtonActive);



        IsGradesNumberInputCorrect isGradesNumberInputCorrect = new IsGradesNumberInputCorrect(this);
        gradesNumberInput.setOnFocusChangeListener(isGradesNumberInputCorrect);

        CreateGradesActivity createGradesActivity = new CreateGradesActivity(this);
        gradesButton.setOnClickListener(createGradesActivity);

        IsSurnameInputCorrect isSurnameInputCorrect = new IsSurnameInputCorrect(this);
        surnameInput.setOnFocusChangeListener(isSurnameInputCorrect);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("enabledGradesButton", gradesButton.isEnabled());
        outState.putString("nameInputText", nameInput.getText().toString());
        outState.putString("surnameInputText", surnameInput.getText().toString());
        outState.putString("gradesNumberInputText", gradesNumberInput.getText().toString());
        outState.putString("gradesButton", gradesButton.getText().toString());
        outState.putString("averageText", averageTextView.getText().toString());
        outState.putBoolean("passed", passed);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        gradesButton.setEnabled(savedInstanceState.getBoolean("enabledGradesButton"));
        nameInput.setText(savedInstanceState.getString("nameInputText"));
        surnameInput.setText(savedInstanceState.getString("surnameInputText"));
        gradesNumberInput.setText(savedInstanceState.getString("gradesNumberInputText"));
        gradesButton.setText(savedInstanceState.getString("gradesButton"));
        averageTextView.setText(savedInstanceState.getString("averageText"));
        passed = savedInstanceState.getBoolean("passed");

        restoreListeners();
    }

    protected void restoreListeners(){
        String failedText = getResources().getString(R.string.gradesButtonFailed);
        String passedText = getResources().getString(R.string.gradesButtonPassed);
        if(gradesButton.getText().equals(failedText) || gradesButton.getText().equals(passedText)){
            gradesButton.setOnClickListener(new EndApplication(this, passed));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ActivityCode.GradesActivity.getValue() && resultCode==RESULT_OK){
            gradesActivityResultOk(data);
        }else if(requestCode == ActivityCode.GradesActivity.getValue() && resultCode==RESULT_CANCELED){
        }
    }

    public void gradesActivityResultOk(Intent data){
        Bundle bundle = data.getExtras();
        double average = bundle.getDouble("average");
        String averageString = String.valueOf(bundle.getDouble("average"));
        String cutAverage = averageString.substring(0, Math.min(averageString.length(), 5));
        String averageText = getApplicationContext().getResources().getString(R.string.averageTextView) + cutAverage;

        changeGradesButton(averageText, average);
    }

    public void changeGradesButton(String averageText, double average){
        averageTextView.setText(averageText);
        passed = false;
        if(average >= 3){
            gradesButton.setText(R.string.gradesButtonPassed);
            passed = true;
        }else {
            gradesButton.setText(R.string.gradesButtonFailed);
        }
        gradesButton.setOnClickListener(new EndApplication(this, passed));
    }

    public EditText getNameInput() {
        return nameInput;
    }

    public EditText getSurnameInput() {
        return surnameInput;
    }

    public EditText getGradesNumberInput() {
        return gradesNumberInput;
    }

    public Button getGradesButton() {
        return gradesButton;
    }

    public TextView getAverageTextView() {
        return averageTextView;
    }
}