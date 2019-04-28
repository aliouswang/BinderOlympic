package com.example.aliouswang.binderolympic;

import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.aliouswang.im.entity.User;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button btn_connect;
    private RecyclerView mRecyclerView;
    private Button btn_unconnect;
    private Button btn_load_list;

    private IMAdapter mIMAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview);
        mIMAdapter = new IMAdapter();
        mRecyclerView.setAdapter(mIMAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn_unconnect = findViewById(R.id.btn_unconnect);
        btn_load_list = findViewById(R.id.btn_load_list);


        btn_connect = findViewById(R.id.btn_connect);
        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectIMService();
            }
        });

        btn_unconnect = findViewById(R.id.btn_unconnect);
        btn_unconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String pid = Process.myPid() + "";
                    IMManager.getInstance().getIMService().unregister(new User(pid, "Jake", "http://img0.pconline.com.cn/pconline/1511/29/7257120_901_thumb.jpg"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        btn_load_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<User> users = IMManager.getInstance().getIMService().getUserList();
                    List<User> newUsers = new ArrayList<>();
                    for (User user : users) {
                        String pid = Process.myPid() + "";
                        if (!pid.equals(user.id)) {
                            newUsers.add(user);
                        }
                    }
                    mIMAdapter.setUserList(newUsers);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void connectIMService() {
        IMManager.getInstance().tryConnect(this);
    }

}
