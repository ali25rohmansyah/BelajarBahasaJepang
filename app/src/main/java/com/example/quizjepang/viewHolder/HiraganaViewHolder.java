package com.example.quizjepang.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizjepang.R;

public class HiraganaViewHolder  extends RecyclerView.ViewHolder {

    public TextView txtNama;
    public ImageView img;

    public HiraganaViewHolder (@NonNull View itemView) {
        super(itemView);

        txtNama = itemView.findViewById(R.id.txtNama);
        img = itemView.findViewById(R.id.img);
    }
}