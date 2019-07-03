package com.example.quizjepang.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizjepang.R;
import com.example.quizjepang.common.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Quiz extends AppCompatActivity implements View.OnClickListener {

    LayoutInflater inflater;
    View dialogView;

    Button btnEasy, btnMedium, btnHard, btnStart, btnCancel;
    DatabaseReference quiz = FirebaseDatabase.getInstance().getReference("quiz");
    DatabaseReference image = FirebaseDatabase.getInstance().getReference("gambar");

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Arkhip_font.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_quiz);

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
        startDialog();

    }

    private void startDialog() {
        loadQuestion();
        android.app.AlertDialog.Builder start = new android.app.AlertDialog.Builder(this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.start, null);

        start.setTitle("Siap ?")
                .setView(dialogView)
                .setIcon(R.drawable.hiragana)
                .setMessage("Setiap soal terdapat waktu 1 menit, pilihlah gambar yg benar dan ejaan yang tepat");

        final android.app.AlertDialog alertDialog = start.create();

        btnStart = dialogView.findViewById(R.id.btnStart);
        btnCancel = dialogView.findViewById(R.id.btnCancel);

        alertDialog.show();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

                if(Common.isConnectedToInternet(getBaseContext())){
                    Intent intent = new Intent(Quiz.this, Playing.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(Quiz.this,"Please check your connections !!",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private void loadQuestion() {

        quiz.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Clear old question
                if (Common.questionList.size() > 0)
                    Common.questionList.clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    com.example.quizjepang.model.Quiz query = postSnapshot.getValue(com.example.quizjepang.model.Quiz.class);
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
