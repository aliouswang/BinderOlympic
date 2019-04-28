package com.example.aliouswang.binderolympic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aliouswang.im.entity.Talk;
import com.aliouswang.im.entity.TalkMessage;
import com.aliouswang.im.entity.User;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SessionDetailActivity extends AppCompatActivity {

    private User mUser;

    public static void start(Context context, User user) {
        Intent intent = new Intent(context, SessionDetailActivity.class);
        intent.putExtra("user", user);
        context.startActivity(intent);
    }

    private RecyclerView mRecyclerView;
    private SessionAdapter mSessionAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUser = getIntent().getParcelableExtra("user");
        setContentView(R.layout.activity_session_detail);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSessionAdapter = new SessionAdapter();
        mRecyclerView.setAdapter(mSessionAdapter);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageArrived(TalkMessage talkMessage) {
        Talk talk = talkMessage.getTalk();
        if (talk != null) {
            mSessionAdapter.addTalk(talk);
        }
    }
}
