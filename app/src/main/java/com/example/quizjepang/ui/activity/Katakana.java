package com.example.quizjepang.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quizjepang.R;
import com.example.quizjepang.common.Common;
import com.example.quizjepang.model.HiraganaModel;
import com.example.quizjepang.viewHolder.HiraganaViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Katakana extends AppCompatActivity {

    private Spinner spName;
    ImageView back, img;

    RecyclerView rKatakana;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<HiraganaModel, HiraganaViewHolder> adapter;
    DatabaseReference hiragana = FirebaseDatabase.getInstance().getReference("katakana");

    String type;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SegoeBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_katakana);

        //Casting
        spName = findViewById(R.id.sName);
        img = findViewById(R.id.img);
        back = findViewById(R.id.back);
        rKatakana = findViewById(R.id.rKatakana);

        rKatakana.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,3);
        rKatakana.setLayoutManager(layoutManager);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Katakana.this, Home.class));
                finish();
            }
        });

        //Check Connections
        if(!Common.isConnectedToInternet(getBaseContext())){
            Toast.makeText(Katakana.this,"Please check your connections !!",Toast.LENGTH_LONG).show();
            return;
        }

        spName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = spName.getSelectedItem().toString();

                if(type.equals("Yoon")){

                    img.setVisibility(View.VISIBLE);
                    img.setImageResource(R.drawable.yoonkatakana);
                    rKatakana.setVisibility(View.INVISIBLE);

                }else if(type.equals("Tenten dan Maru")){

                    img.setVisibility(View.VISIBLE);
                    img.setImageResource(R.drawable.tentenmarukatakana);
                    rKatakana.setVisibility(View.INVISIBLE);

                }else{

                    img.setVisibility(View.INVISIBLE);
                    rKatakana.setVisibility(View.VISIBLE);

                    adapter = new FirebaseRecyclerAdapter<HiraganaModel, HiraganaViewHolder>(
                            HiraganaModel.class,
                            R.layout.item_hiragana,
                            HiraganaViewHolder.class,
                            hiragana.orderByChild("tipe").equalTo(type)
                    ) {
                        @Override
                        protected void populateViewHolder(HiraganaViewHolder viewHolder, HiraganaModel model, int position) {
                            Glide.with(Katakana.this)
                                    .load(model.getGambar())
                                    .into(viewHolder.img);
                            viewHolder.txtNama.setText(model.getNama());
                        }
                    };
                    adapter.notifyDataSetChanged();
                    rKatakana.setAdapter(adapter);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(Katakana.this, Home.class));
        finish();
    }
}
