package com.example.aliouswang.binderolympic;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliouswang.im.entity.Talk;

import java.util.ArrayList;
import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionViewHolder> {

    private List<Talk> dataList = new ArrayList<>();

    public void addTalk(Talk talk) {
        this.dataList.add(talk);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SessionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_session, viewGroup, false);
        return new SessionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder sessionViewHolder, int i) {
        sessionViewHolder.bind(dataList.get(i));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
