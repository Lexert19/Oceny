package com.example.oceny.listener;

import android.view.View;

import com.example.oceny.GradesActivity;

public class CalculateAverage implements View.OnClickListener {
    private GradesActivity context;

    public CalculateAverage(GradesActivity context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        context.returnAverage();
    }
}
