package com.oussa.chfeha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class Manual extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);




        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = ((EditText)findViewById(R.id.code)).getText().toString();
                if(!code.isEmpty()) {
                    Intent i = new Intent(Manual.this, Result.class);
                    i.putExtra("code", code);
                    startActivity(i);
                }
            }
        });
    }
}
