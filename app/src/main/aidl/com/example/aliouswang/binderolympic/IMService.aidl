// IMService.aidl
package com.example.aliouswang.binderolympic;

// Declare any non-default types here with import statements

import com.example.aliouswang.binderolympic.entity.Talk;

interface IMService {
//    /**
//     * Demonstrates some basic types that you can use as parameters
//     * and return values in AIDL.
//     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    int init();

    void sendMessage(in Talk talk);
}
