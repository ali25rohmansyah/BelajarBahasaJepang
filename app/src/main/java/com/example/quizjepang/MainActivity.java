package com.example.quizjepang;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quizjepang.common.Common;
import com.example.quizjepang.model.Quiz;
import com.example.quizjepang.ui.activity.Playing;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnEasy, btnMedium, btnHard;
    DatabaseReference quiz = FirebaseDatabase.getInstance().getReference("quiz");
    DatabaseReference image = FirebaseDatabase.getInstance().getReference("gambar");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEasy = findViewById(R.id.btnEasy);
        btnMedium = findViewById(R.id.btnMedium);
        btnHard = findViewById(R.id.btnHard);

        btnEasy.setOnClickListener(this);
        btnMedium.setOnClickListener(this);
        btnHard.setOnClickListener(this);

        loadQuestion();
    }

    @Override
    public void onClick(View v) {

        Button clickedButton = (Button)v;
        Common.level = String.valueOf(clickedButton.getText());
        startActivity(new Intent(MainActivity.this, Playing.class));

    }

    private void loadQuestion() {

        quiz.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Clear old question
                if (Common.questionList.size() > 0)
                    Common.questionList.clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Quiz query = postSnapshot.getValue(Quiz.class);
                    Common.questionList.add(query);
                }
                //Shuffle Question
                Collections.shuffle(Common.questionList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        image.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data = snapshot.getValue(String.class);
                    Common.imageList.add(data);
                }
                //Shuffle Image
                Collections.shuffle(Common.imageList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
