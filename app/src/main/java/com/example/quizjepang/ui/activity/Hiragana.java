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

public class Hiragana extends AppCompatActivity {

    private Spinner spName;
    ImageView back, img;

    RecyclerView rHiragana;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<HiraganaModel, HiraganaViewHolder> adapter;
    DatabaseReference hiragana = FirebaseDatabase.getInstance().getReference("hiragana");

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
        setContentView(R.layout.activity_hiragana);

        spName = findViewById(R.id.sName);
        img = findViewById(R.id.img);
        back = findViewById(R.id.back);

        rHiragana = findViewById(R.id.rHiragana);
        rHiragana.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,3);
        rHiragana.setLayoutManager(layoutManager);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hiragana.this, Home.class));
                finish();
            }
        });

        if(!Common.isConnectedToInternet(getBaseContext())){
            Toast.makeText(Hiragana.this,"Please check your connections !!",Toast.LENGTH_LONG).show();
            return;
        }

        spName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = spName.getSelectedItem().toString();

                if(type.equals("Yoon")){
                    img.setVisibility(View.VISIBLE);
                    img.setImageResource(R.drawable.yoonhiragana);
                    rHiragana.setVisibility(View.INVISIBLE);
                }else{

                    img.setVisibility(View.INVISIBLE);
                    rHiragana.setVisibility(View.VISIBLE);

                    adapter = new FirebaseRecyclerAdapter<HiraganaModel, HiraganaViewHolder>(
                            HiraganaModel.class,
                            R.layout.item_hiragana,
                            HiraganaViewHolder.class,
                            hiragana.orderByChild("tipe").equalTo(type)
                    ) {
                        @Override
                        protected void populateViewHolder(HiraganaViewHolder viewHolder, HiraganaModel model, int position) {
                            Glide.with(Hiragana.this)
                                    .load(model.getGambar())
                                    .into(viewHolder.img);
                            viewHolder.txtNama.setText(model.getNama());
                        }
                    };
                    adapter.notifyDataSetChanged();
                    rHiragana.setAdapter(adapter);

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

        startActivity(new Intent(Hiragana.this, Home.class));
        finish();
    }
}
