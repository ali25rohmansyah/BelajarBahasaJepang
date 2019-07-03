package com.example.quizjepang.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizjepang.R;

public class Done extends AppCompatActivity {

    TextView txtScore;
    Button btnTry, btnHome;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        txtScore = findViewById(R.id.txtScore);
        btnTry = findViewById(R.id.btnTry);
        btnHome = findViewById(R.id.btnHome);


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Done.this, Home.class));
                finish();
            }
        });
        btnTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Done.this, Playing.class));
                finish();
            }
        });

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            score = extra.getInt("QuestionScore");
            txtScore.setText(String.format("Question Score : %d", score));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Done.this, Quiz.class));
        finish();
    }
}
