// IUserProxy.aidl
package com.aliouswang.im;

// Declare any non-default types here with import statements

import com.aliouswang.im.entity.Talk;

interface IUserProxy {

    void pushMessage(inout Talk talk);

}

