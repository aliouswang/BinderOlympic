package com.example.aliouswang.binderolympic;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.aliouswang.im.IMService;
import com.aliouswang.im.IUserProxy;
import com.aliouswang.im.entity.Talk;
import com.aliouswang.im.entity.User;
import com.aliouswang.im.remote.IMRemoteService;
import com.orhanobut.logger.Logger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button btn_connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_connect = findViewById(R.id.btn_connect);
        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectIMService();
            }
        });
    }

    private void connectIMService() {
        Intent intent = new Intent(this, IMRemoteService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                IMService imService =  IMService.Stub.asInterface(service);

                try {
//                    int pid = imService.init();
//                    Logger.d("pid:" + pid);

                    String pid = Process.myPid() + "";
                    User user = new User(pid, "Jake", "http://img0.pconline.com.cn/pconline/1511/29/7257120_901_thumb.jpg");
                    imService.register(user, new IUserProxy() {
                        @Override
                        public void pushMessage(Talk talk) throws RemoteException {

                        }

                        @Override
                        public IBinder asBinder() {
                            return null;
                        }
                    });
                    List<String> userList = imService.getUserList();
                    Logger.w(userList.toString());
                    imService.asBinder().linkToDeath(new IBinder.DeathRecipient() {
                        @Override
                        public void binderDied() {

                        }
                    }, 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

}
