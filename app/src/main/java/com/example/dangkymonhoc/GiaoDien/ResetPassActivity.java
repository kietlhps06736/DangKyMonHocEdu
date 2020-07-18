package com.example.dangkymonhoc.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dangkymonhoc.R;

public class ResetPassActivity extends AppCompatActivity {
    Button btnReset;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        imgBack = findViewById(R.id.img_back);
        btnReset = findViewById(R.id.btnReset);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResetPassActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResetPassActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}