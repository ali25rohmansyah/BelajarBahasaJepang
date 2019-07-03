package com.example.quizjepang.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizjepang.R;
import com.example.quizjepang.common.Common;
import com.example.quizjepang.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Register extends AppCompatActivity {

    Button btnSubmit;
    EditText edtNama;
    DatabaseReference Tuser = FirebaseDatabase.getInstance().getReference("user");

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
        setContentView(R.layout.activity_register);

        btnSubmit = findViewById(R.id.btnSubmit);
        edtNama = findViewById(R.id.edtNama);
        Paper.init(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Common.isConnectedToInternet(getBaseContext())){

                    btnSubmit.setVisibility(View.INVISIBLE);

                    Tuser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child(edtNama.getText().toString()).exists()){
                                Toast.makeText(Register.this,"Nama sudah ada!!",Toast.LENGTH_SHORT).show();
                                btnSubmit.setVisibility(View.VISIBLE);
                            }else{
                                User user = new User(0);
                                Tuser.child(edtNama.getText().toString()).setValue(user);

                                //save data
                                Paper.book().write(Common.User_Name, edtNama.getText().toString());
                                Toast.makeText(Register.this,"Sukses mendaftar",Toast.LENGTH_SHORT).show();
                                btnSubmit.setVisibility(View.VISIBLE);
                                startActivity(new Intent(Register.this, Home.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }else{
                    Toast.makeText(Register.this,"Please check your connections !!",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

    }
}
