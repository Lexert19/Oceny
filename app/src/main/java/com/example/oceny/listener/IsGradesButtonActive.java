package com.example.oceny.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;

import com.example.oceny.MainActivity;

public class IsGradesButtonActive implements TextWatcher {
    private MainActivity context;

    public IsGradesButtonActive(MainActivity context) {
        this.context = context;
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        int gradesNumber =0;
        try {
            gradesNumber = Integer.parseInt(context.getGradesNumberInput().getText().toString());
        }catch (Exception e){}

        context.getGradesButton().setEnabled(false);
        if(!(gradesNumber >= 5 && gradesNumber <= 15))
            return;
        if(context.getSurnameInput().getText().toString().length() == 0)
            return;
        if(context.getNameInput().getText().toString().length() == 0)
            return;

        context.getGradesButton().setEnabled(true);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
