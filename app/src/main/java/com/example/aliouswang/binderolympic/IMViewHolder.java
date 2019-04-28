package com.example.aliouswang.binderolympic;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aliouswang.im.entity.User;
import com.squareup.picasso.Picasso;

public class IMViewHolder extends RecyclerView.ViewHolder {

    ImageView img_user;
    TextView tv_user_name;

    public IMViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_user_name = itemView.findViewById(R.id.tv_user_name);
        img_user = itemView.findViewById(R.id.img_user);
    }

    public void bind(final User user) {
        Picasso.get().load(user.head_url).into(img_user);
        tv_user_name.setText(user.name);

        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionDetailActivity.start(itemView.getContext(), user);
            }
        });
    }

}
