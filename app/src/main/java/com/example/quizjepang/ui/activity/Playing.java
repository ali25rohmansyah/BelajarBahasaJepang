package com.example.quizjepang.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quizjepang.R;
import com.example.quizjepang.common.Common;

import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Playing extends AppCompatActivity implements View.OnClickListener {

    LayoutInflater inflater;
    View dialogView;

    FrameLayout frameLayout;
    View level_easy, level_hard, level_medium;
    ImageView img1H, img2H, img3H, img4H, img1E, img2E, img1M, img2M, img3M;
    TextView txtScore, txtQuestion, txtTotalQuestion, txtTimer;
    EditText edtLatin;
    Button btnNext, btnYes, btnNo;

    CountDownTimer countDownTimer;
    final static long INTERVAL = 1000; // 1 sec
    final static long TIMEOUT = 60000;  // 1 menit

    int index=0, score=0, thisQuestion=0, totalQuestion, correctAnswer, imageScore, progressValue = 61;;
    int i;
    Random r;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Georgia.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_playing);

        r = new Random();
        i = r.nextInt((Common.imageList.size()-3) + 1);

        //Views
        frameLayout = findViewById(R.id.frameLayout);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtScore = findViewById(R.id.txtScore);
        txtTotalQuestion = findViewById(R.id.txtTotalQuestion);
        txtTimer = findViewById(R.id.txtTimer);
        edtLatin = findViewById(R.id.edtLatin);
        btnNext = findViewById(R.id.btnNext);

        level_easy = getLayoutInflater().inflate(R.layout.level_easy, frameLayout, false);
        img1E = level_easy.findViewById(R.id.img1E);
        img2E = level_easy.findViewById(R.id.img2E);

        level_medium = getLayoutInflater().inflate(R.layout.level_medium, frameLayout, false);
        img1M = level_medium.findViewById(R.id.img1M);
        img2M = level_medium.findViewById(R.id.img2M);
        img3M = level_medium.findViewById(R.id.img3M);

        level_hard = getLayoutInflater().inflate(R.layout.level_hard, frameLayout, false);
        img1H = level_hard.findViewById(R.id.img1H);
        img2H = level_hard.findViewById(R.id.img2H);
        img3H = level_hard.findViewById(R.id.img3H);
        img4H = level_hard.findViewById(R.id.img4H);

        img1E.setOnClickListener(this);
        img2E.setOnClickListener(this);
        img1M.setOnClickListener(this);
        img2M.setOnClickListener(this);
        img3M.setOnClickListener(this);
        img1H.setOnClickListener(this);
        img2H.setOnClickListener(this);
        img3H.setOnClickListener(this);
        img4H.setOnClickListener(this);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                progressValue=61;
                txtTimer.setText(String.valueOf(progressValue));

                i = r.nextInt((Common.imageList.size()-3) + 1);
                if(index<totalQuestion){
                    img1E.setBackgroundResource(R.color.putih);
                    img2E.setBackgroundResource(R.color.putih);
                    img1M.setBackgroundResource(R.color.putih);
                    img2M.setBackgroundResource(R.color.putih);
                    img3M.setBackgroundResource(R.color.putih);
                    img1H.setBackgroundResource(R.color.putih);
                    img2H.setBackgroundResource(R.color.putih);
                    img3H.setBackgroundResource(R.color.putih);
                    img4H.setBackgroundResource(R.color.putih);

                    if(edtLatin.getText().toString().equals(Common.questionList.get(index).getKata())){
                        //CorrecAnswer;
                        score  +=(1+imageScore) ;
                        imageScore = 0;
                        correctAnswer++;
                        showQuestion(++index);
                    }else{
                        //Wrong Answer
                        score += imageScore;
                        imageScore = 0;
                        correctAnswer += 0;
                        showQuestion(++index);
                    }
                    txtScore.setText(String.format("%d",score));
                }
            }
        });
    }

    @Override
        public void onClick(View v) {

        ImageView clickedImage = (ImageView) v;
        switch(v.getId()) {

            case R.id.img1E:
                clickedImage.setBackgroundResource(R.color.abu);
                img2E.setBackgroundResource(R.color.putih);
                img1M.setBackgroundResource(R.color.putih);
                img2M.setBackgroundResource(R.color.putih);
                img3M.setBackgroundResource(R.color.putih);
                img1H.setBackgroundResource(R.color.putih);
                img2H.setBackgroundResource(R.color.putih);
                img3H.setBackgroundResource(R.color.putih);
                img4H.setBackgroundResource(R.color.putih);
                imageScore = 1;
            break;
            case R.id.img2E:
                clickedImage.setBackgroundResource(R.color.abu);
                img1E.setBackgroundResource(R.color.putih);
                img1M.setBackgroundResource(R.color.putih);
                img2M.setBackgroundResource(R.color.putih);
                img3M.setBackgroundResource(R.color.putih);
                img1H.setBackgroundResource(R.color.putih);
                img2H.setBackgroundResource(R.color.putih);
                img3H.setBackgroundResource(R.color.putih);
                img4H.setBackgroundResource(R.color.putih);
                imageScore = 0;
                break;
            case R.id.img1M:
                clickedImage.setBackgroundResource(R.color.abu);
                img2E.setBackgroundResource(R.color.putih);
                img1E.setBackgroundResource(R.color.putih);
                img2M.setBackgroundResource(R.color.putih);
                img3M.setBackgroundResource(R.color.putih);
                img1H.setBackgroundResource(R.color.putih);
                img2H.setBackgroundResource(R.color.putih);
                img3H.setBackgroundResource(R.color.putih);
                img4H.setBackgroundResource(R.color.putih);
                imageScore=0;
                break;
            case R.id.img2M:
                clickedImage.setBackgroundResource(R.color.abu);
                img2E.setBackgroundResource(R.color.putih);
                img1M.setBackgroundResource(R.color.putih);
                img1E.setBackgroundResource(R.color.putih);
                img3M.setBackgroundResource(R.color.putih);
                img1H.setBackgroundResource(R.color.putih);
                img2H.setBackgroundResource(R.color.putih);
                img3H.setBackgroundResource(R.color.putih);
                img4H.setBackgroundResource(R.color.putih);
                imageScore=1;
                break;
            case R.id.img3M:
                clickedImage.setBackgroundResource(R.color.abu);
                img2E.setBackgroundResource(R.color.putih);
                img1M.setBackgroundResource(R.color.putih);
                img2M.setBackgroundResource(R.color.putih);
                img1E.setBackgroundResource(R.color.putih);
                img1H.setBackgroundResource(R.color.putih);
                img2H.setBackgroundResource(R.color.putih);
                img3H.setBackgroundResource(R.color.putih);
                img4H.setBackgroundResource(R.color.putih);
                imageScore=0;
                break;
            case R.id.img1H:
                clickedImage.setBackgroundResource(R.color.abu);
                img2E.setBackgroundResource(R.color.putih);
                img1M.setBackgroundResource(R.color.putih);
                img2M.setBackgroundResource(R.color.putih);
                img3M.setBackgroundResource(R.color.putih);
                img1E.setBackgroundResource(R.color.putih);
                img2H.setBackgroundResource(R.color.putih);
                img3H.setBackgroundResource(R.color.putih);
                img4H.setBackgroundResource(R.color.putih);
                imageScore=0;
                break;
            case R.id.img2H:
                clickedImage.setBackgroundResource(R.color.abu);
                img2E.setBackgroundResource(R.color.putih);
                img1M.setBackgroundResource(R.color.putih);
                img2M.setBackgroundResource(R.color.putih);
                img3M.setBackgroundResource(R.color.putih);
                img1H.setBackgroundResource(R.color.putih);
                img1E.setBackgroundResource(R.color.putih);
                img3H.setBackgroundResource(R.color.putih);
                img4H.setBackgroundResource(R.color.putih);
                imageScore=0;
                break;
            case R.id.img3H:
                clickedImage.setBackgroundResource(R.color.abu);
                img2E.setBackgroundResource(R.color.putih);
                img1M.setBackgroundResource(R.color.putih);
                img2M.setBackgroundResource(R.color.putih);
                img3M.setBackgroundResource(R.color.putih);
                img1H.setBackgroundResource(R.color.putih);
                img2H.setBackgroundResource(R.color.putih);
                img1E.setBackgroundResource(R.color.putih);
                img4H.setBackgroundResource(R.color.putih);
                imageScore=0;
                break;
            case R.id.img4H:
                clickedImage.setBackgroundResource(R.color.abu);
                img2E.setBackgroundResource(R.color.putih);
                img1M.setBackgroundResource(R.color.putih);
                img2M.setBackgroundResource(R.color.putih);
                img3M.setBackgroundResource(R.color.putih);
                img1H.setBackgroundResource(R.color.putih);
                img2H.setBackgroundResource(R.color.putih);
                img3H.setBackgroundResource(R.color.putih);
                img1E.setBackgroundResource(R.color.putih);
                imageScore=1;
                break;
        }

    }

    private void showQuestion(int index) {

        if(index < totalQuestion){
            timer();
            thisQuestion++;
            txtTotalQuestion.setText(String.format("%d / %d", thisQuestion,totalQuestion));

            switch (Common.level) {
                case "Easy":
                    frameLayout.removeView(level_easy);
                    frameLayout.removeView(level_medium);
                    frameLayout.removeView(level_hard);
                    frameLayout.addView(level_easy);
                    txtQuestion.setText(Common.questionList.get(index).getSoal());
                    Glide.with(getBaseContext())
                            .load(Common.questionList.get(index).getImage())
                            .into(img1E);
                    Glide.with(getBaseContext())
                            .load(Common.imageList.get(i))
                            .into(img2E);
                    break;
                case "Medium":
                    frameLayout.removeView(level_easy);
                    frameLayout.removeView(level_medium);
                    frameLayout.removeView(level_hard);
                    frameLayout.addView(level_medium);
                    txtQuestion.setText(Common.questionList.get(index).getSoal());
                    Glide.with(getBaseContext())
                            .load(Common.imageList.get(i))
                            .into(img1M);
                    Glide.with(getBaseContext())
                            .load(Common.questionList.get(index).getImage())
                            .into(img2M);
                    Glide.with(getBaseContext())
                            .load(Common.imageList.get(i+1))
                            .into(img3M);
                    break;
                case "Hard":
                    frameLayout.removeView(level_easy);
                    frameLayout.removeView(level_medium);
                    frameLayout.removeView(level_hard);
                    frameLayout.addView(level_hard);
                    txtQuestion.setText(Common.questionList.get(index).getSoal());
                    Glide.with(getBaseContext())
                            .load(Common.imageList.get(i+1))
                            .into(img1H);
                    Glide.with(getBaseContext())
                            .load(Common.imageList.get(i))
                            .into(img2H);
                    Glide.with(getBaseContext())
                            .load(Common.imageList.get(i+2))
                            .into(img3H);
                    Glide.with(getBaseContext())
                            .load(Common.questionList.get(index).getImage())
                            .into(img4H);
                    break;
            }
        }else{
            //Final Question
            Intent intent = new Intent(Playing.this, Done.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("QuestionScore", score);
            intent.putExtras(dataSend);

            startActivity(intent);
            finish();

        }
    }

    private void timer() {

        countDownTimer = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long miniSec) {
                progressValue--;
                txtTimer.setText(String.valueOf(progressValue));
            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
                i = r.nextInt((Common.imageList.size()-3) + 1);
                progressValue=61;

                img1E.setBackgroundResource(R.color.putih);
                img2E.setBackgroundResource(R.color.putih);
                img1M.setBackgroundResource(R.color.putih);
                img2M.setBackgroundResource(R.color.putih);
                img3M.setBackgroundResource(R.color.putih);
                img1H.setBackgroundResource(R.color.putih);
                img2H.setBackgroundResource(R.color.putih);
                img3H.setBackgroundResource(R.color.putih);
                img1E.setBackgroundResource(R.color.putih);

                showQuestion(++index);
            }
        }.start();
    }

    private void pauseDialog() {

        android.app.AlertDialog.Builder start = new android.app.AlertDialog.Builder(this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.start, null);

        start.setTitle("Pause")
                .setView(dialogView)
                .setIcon(R.drawable.katakana)
                .setMessage("Are you sure want to leave ?");

        final android.app.AlertDialog alertDialog = start.create();

        btnYes = dialogView.findViewById(R.id.btnStart);
        btnNo = dialogView.findViewById(R.id.btnCancel);

        btnYes.setText("Yes");
        btnNo.setText("No");

        alertDialog.show();
        countDownTimer.cancel();

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                //Final Question
                Intent intent = new Intent(Playing.this, Done.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("QuestionScore", score);
                intent.putExtras(dataSend);

                startActivity(intent);
                finish();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                countDownTimer.start();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

            totalQuestion = 20;
            showQuestion(index);

    }

    @Override
    public void onBackPressed() {
        pauseDialog();
    }
}
