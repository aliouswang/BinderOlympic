// IMService.aidl
package com.aliouswang.im;

// Declare any non-default types here with import statements

import com.aliouswang.im.entity.Talk;
import com.aliouswang.im.entity.User;
import com.aliouswang.im.IUserProxy;

interface IMService {

    void register(in User user, IUserProxy userProxy);

    void unregister(in User user);

    void sendMessage(String pid, in Talk talk);

    List<String> getUserList();

}
