package com.example.oceny.listener;

import android.content.Context;
import android.view.View;

import com.example.oceny.GradesActivity;

public class CalculateAverageButtonListener implements View.OnClickListener {
    private GradesActivity context;

    public CalculateAverageButtonListener(GradesActivity context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        context.returnAverage();
    }
}
