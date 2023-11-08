package com.example.oceny;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText nameInput;
    private EditText surnameInput;
    private EditText gradesNumberInput;
    private Button gradesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameInput = findViewById(R.id.nameInput);
        surnameInput = findViewById(R.id.surnameInput);
        gradesNumberInput = findViewById(R.id.gradesNumberInput);
        gradesButton = findViewById(R.id.gradesButton);




        nameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    nameInput.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                    //nameInput.setError(null);
                }else if(nameInput.getText().toString().length() == 0){
                    nameInput.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    String error = getApplicationContext().getResources().getString(R.string.nameInputError);
                    nameInput.setError(error);
                }


            }
        });
        nameInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                changeGradesButtonStatus();
                return false;
            }
        });
        surnameInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                changeGradesButtonStatus();
                return false;
            }
        });
        gradesNumberInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                changeGradesButtonStatus();
                return false;
            }
        });


        gradesNumberInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int gradesNumber =0;
                try {
                    gradesNumber = Integer.parseInt(gradesNumberInput.getText().toString());
                }catch (Exception e){}

                if(hasFocus){
                    gradesNumberInput.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                    //gradesNumberInput.setError(null);
                }else if(gradesNumber >= 5 && gradesNumber <= 15){

                }else {
                    gradesNumberInput.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    String error = getApplicationContext().getResources().getString(R.string.gradesNumberInputError);
                    gradesNumberInput.setError(error);
                }
            }
        });

        gradesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GradesActivity.class);
                intent.putExtra("degreesNumber", Integer.parseInt(gradesNumberInput.getText().toString()));
                startActivityForResult(intent, ActivityCode.DegreesActivity.getValue());
            }
        });

        surnameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus)  {
                if(hasFocus){
                    surnameInput.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                    //surnameInput.setError(null);
                }else if(surnameInput.getText().toString().length() == 0) {
                    surnameInput.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    String error = getApplicationContext().getResources().getString(R.string.nameInputError);
                    surnameInput.setError(error);
                }
            }
        });


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("enabledGradesButton", gradesButton.isEnabled());
        outState.putString("nameInputText", nameInput.getText().toString());
        outState.putString("surnameInputText", surnameInput.getText().toString());
        outState.putString("gradesNumberInputText", gradesNumberInput.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        gradesButton.setEnabled(savedInstanceState.getBoolean("enabledGradesButton"));
        nameInput.setText(savedInstanceState.getString("nameInputText"));
        surnameInput.setText(savedInstanceState.getString("surnameInputText"));
        gradesNumberInput.setText(savedInstanceState.getString("gradesNumberInputText"));

    }

    public void changeGradesButtonStatus(){
        int gradesNumber =0;
        try {
            gradesNumber = Integer.parseInt(gradesNumberInput.getText().toString());
        }catch (Exception e){}

        gradesButton.setEnabled(false);
        if(!(gradesNumber >= 5 && gradesNumber <= 15))
            return;
        if(surnameInput.getText().toString().length() == 0)
            return;
        if(nameInput.getText().toString().length() == 0)
            return;

        gradesButton.setEnabled(true);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ActivityCode.DegreesActivity.getValue() && resultCode==RESULT_OK){
            Bundle gradesList = data.getExtras();
            Log.d("", String.valueOf(gradesList.getInt("1")));
        }
    }

}