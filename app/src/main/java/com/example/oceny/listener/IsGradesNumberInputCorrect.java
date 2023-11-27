package com.example.oceny.listener;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;

import com.example.oceny.MainActivity;
import com.example.oceny.R;

public class IsGradesNumberInputCorrect implements View.OnFocusChangeListener{
    private MainActivity context;

    public IsGradesNumberInputCorrect(MainActivity context) {
        this.context = context;
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        int gradesNumber =0;
        try {
            gradesNumber = Integer.parseInt(context.getGradesNumberInput().getText().toString());
        }catch (Exception e){}

        if(hasFocus){
            context.getGradesNumberInput().setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
            //gradesNumberInput.setError(null);
        }else if(gradesNumber >= 5 && gradesNumber <= 15){

        }else {
            context.getGradesNumberInput().setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            String error = context.getApplicationContext().getResources().getString(R.string.gradesNumberInputError);
            context.getGradesNumberInput().setError(error);
        }
    }
}
