package com.example.oceny.listener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import com.example.oceny.MainActivity;
import com.example.oceny.R;

public class EndApplication implements View.OnClickListener {
    private MainActivity context;
    private boolean passed;

    public EndApplication(MainActivity mainActivity, boolean passed) {
        this.context = mainActivity;
        this.passed = passed;
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.endApplicationTitle);
        if(passed){
            builder.setMessage(R.string.passedMessage);
        }else {
            builder.setMessage(R.string.failedMessage);
        }
        builder.setPositiveButton(R.string.endApplicationOk, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
