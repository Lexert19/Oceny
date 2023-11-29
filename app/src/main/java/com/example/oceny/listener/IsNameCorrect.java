package com.example.oceny.listener;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;

import com.example.oceny.MainActivity;
import com.example.oceny.R;

public class IsNameCorrect  implements View.OnFocusChangeListener{
    private MainActivity context;

    public IsNameCorrect(MainActivity context) {
        this.context = context;
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if(hasFocus){
            context.getNameInput().setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
        }else if(context.getNameInput().getText().toString().length() == 0){
            context.getNameInput().setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            String error = context.getApplicationContext().getResources().getString(R.string.nameInputError);
            context.getNameInput().setError(error);
        }

    }
}
