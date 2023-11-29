package com.example.oceny.listener;

import android.content.Intent;
import android.view.View;

import com.example.oceny.ActivityCode;
import com.example.oceny.GradesActivity;
import com.example.oceny.MainActivity;

public class CreateGradesActivity implements View.OnClickListener {
    private MainActivity context;

    public CreateGradesActivity(MainActivity context) {
        this.context = context;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, GradesActivity.class);
        intent.putExtra("gradesNumber", Integer.parseInt( context.getGradesNumberInput().getText().toString()));
        context.startActivityForResult(intent, ActivityCode.GradesActivity.getValue());

    }
}
