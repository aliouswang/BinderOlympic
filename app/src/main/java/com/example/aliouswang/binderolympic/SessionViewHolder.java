package com.example.aliouswang.binderolympic;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aliouswang.im.entity.Talk;
import com.squareup.picasso.Picasso;

public class SessionViewHolder extends RecyclerView.ViewHolder {

    ImageView img_user;
    TextView tv_user_name;

    public SessionViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_user_name = itemView.findViewById(R.id.tv_user_name);
        img_user = itemView.findViewById(R.id.img_user);
    }

    public void bind(Talk talk) {
        Picasso.get().load(talk.fromUserHead).into(img_user);
        tv_user_name.setText(talk.fromName);
    }

}
