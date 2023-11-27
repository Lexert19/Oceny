package com.example.oceny.listener;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;

import com.example.oceny.MainActivity;
import com.example.oceny.R;

public class IsSurnameInputCorrect implements View.OnFocusChangeListener{
    private MainActivity context;

    public IsSurnameInputCorrect(MainActivity context) {
        this.context = context;
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if(hasFocus){
            context.getSurnameInput().setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
            //surnameInput.setError(null);
        }else if(context.getSurnameInput().getText().toString().length() == 0) {
            context.getSurnameInput().setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            String error = context.getApplicationContext().getResources().getString(R.string.surnameInputError);
            context.getSurnameInput().setError(error);
        }
    }
}
