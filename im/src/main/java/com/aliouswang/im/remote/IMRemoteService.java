package com.aliouswang.im.remote;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.aliouswang.im.IMService;
import com.aliouswang.im.IUserProxy;
import com.aliouswang.im.entity.Talk;
import com.aliouswang.im.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IMRemoteService extends Service {

    private List<User> mUserList = new ArrayList<>();
    private HashMap<User, IUserProxy> mUserProxyHashMap = new HashMap<>();

    private class IMServiceManager extends IMService.Stub {
        @Override
        public void register(User user, IUserProxy userProxy) throws RemoteException {
            if (checkStatus(user)) {
                return;
            }
            mUserList.add(user);
            mUserProxyHashMap.put(user, userProxy);
        }

        @Override
        public void unregister(User user) throws RemoteException {
            mUserList.remove(user);
            mUserProxyHashMap.remove(user);
        }

        @Override
        public void sendMessage(String pid, Talk talk) throws RemoteException {

        }

        @Override
        public List<User> getUserList() throws RemoteException {
//            List<String> userIdList = new ArrayList<>();
//            for (User user : mUserList) {
//                userIdList.add(user.id);
//            }
            return mUserList;
        }

        private boolean checkStatus(User newUser) {
            boolean hasRegister = false;
            for (User user : mUserList) {
                if (newUser.id.equals(user.id)) {
                    hasRegister = true;
                    break;
                }
            }
            return hasRegister;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IMServiceManager();
    }

}
