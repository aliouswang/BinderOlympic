package com.example.aliouswang.binderolympic;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliouswang.im.entity.User;

import java.util.ArrayList;
import java.util.List;

public class IMAdapter extends RecyclerView.Adapter<IMViewHolder> {

    private List<User> mUserList = new ArrayList<>();

    public void setUserList(List<User> userList) {
        this.mUserList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IMViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_session, viewGroup, false);
        return new IMViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IMViewHolder imViewHolder, int i) {
        imViewHolder.bind(mUserList.get(i));
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

}
