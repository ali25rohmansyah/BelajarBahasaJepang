package com.example.quizjepang.ui.splashScreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.quizjepang.R;
import com.example.quizjepang.common.Common;
import com.example.quizjepang.ui.activity.Home;
import com.example.quizjepang.ui.activity.Register;

import io.paperdb.Paper;

public class SplashScreen extends AppCompatActivity {

    TextView sl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        sl = findViewById(R.id.sl);
        Paper.init(this);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String user = Paper.book().read(Common.User_Name);

                if(user != null){
                    if(!user.isEmpty()){
                        startActivity(new Intent(SplashScreen.this, Home.class));
                        finish();
                    }
                }else {
                    startActivity(new Intent(SplashScreen.this, Register.class));
                    finish();
                }
            }
        }, 2000L);



    }
}
