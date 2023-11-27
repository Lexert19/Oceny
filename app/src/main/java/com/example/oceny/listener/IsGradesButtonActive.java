package com.example.oceny.listener;

import android.view.KeyEvent;
import android.view.View;

import com.example.oceny.MainActivity;

public class IsGradesButtonActive implements View.OnKeyListener {
    private MainActivity context;

    public IsGradesButtonActive(MainActivity context) {
        this.context = context;
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        int gradesNumber =0;
        try {
            gradesNumber = Integer.parseInt(context.getGradesNumberInput().getText().toString());
        }catch (Exception e){}

        context.getGradesButton().setEnabled(false);
        if(!(gradesNumber >= 5 && gradesNumber <= 15))
            return false;
        if(context.getSurnameInput().getText().toString().length() == 0)
            return false;
        if(context.getNameInput().getText().toString().length() == 0)
            return false;

        context.getGradesButton().setEnabled(true);
        return true;
    }
}
