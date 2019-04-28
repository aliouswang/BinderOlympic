package com.example.aliouswang.binderolympic;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;

import com.aliouswang.im.IMService;
import com.aliouswang.im.IUserProxy;
import com.aliouswang.im.entity.Talk;
import com.aliouswang.im.entity.TalkMessage;
import com.aliouswang.im.entity.User;
import com.aliouswang.im.remote.IMRemoteService;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class IMManager {

    private IMService mIMService;

    private IMManager() {}

    public static IMManager getInstance() {
        return Inner.instace;
    }

    private static class Inner {
        private static IMManager instace = new IMManager();
    }

    public boolean isConnected() {
        return mIMService != null;
    }

    public IMService getIMService() {
        return this.mIMService;
    }

    public void tryConnect(Context context) {
        if (isConnected()) return;
        Intent intent = new Intent(context, IMRemoteService.class);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                IMService imService =  IMService.Stub.asInterface(service);
                mIMService = imService;
                try {
                    String pid = Process.myPid() + "";
                    User user = new User(pid, "Jake", "http://img0.pconline.com.cn/pconline/1511/29/7257120_901_thumb.jpg");
                    imService.register(user, new IUserProxy() {
                        @Override
                        public void pushMessage(Talk talk) throws RemoteException {
                            EventBus.getDefault().post(new TalkMessage(talk));
                        }

                        @Override
                        public IBinder asBinder() {
                            return null;
                        }
                    });
                    List<User> userList = imService.getUserList();
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
        }, context.BIND_AUTO_CREATE);
    }

}
