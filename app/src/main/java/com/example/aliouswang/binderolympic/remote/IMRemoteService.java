package com.example.aliouswang.binderolympic.remote;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.aliouswang.binderolympic.IMService;
import com.example.aliouswang.binderolympic.entity.Talk;

import java.util.Random;

public class IMRemoteService extends Service {

    private class IMServiceManager extends IMService.Stub {

        @Override
        public int init() throws RemoteException {
            return new Random().nextInt();
        }

        @Override
        public void sendMessage(Talk talk) throws RemoteException {

        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IMServiceManager();
    }

}
