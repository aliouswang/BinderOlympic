package com.example.aliouswang.binderolympic.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Talk implements Parcelable {

    public long timeStamp;
    public int fromId;
    public String fromName;
    public String fromUserHead;
    public int toId;

    public String content;

    public Talk() {

    }

    protected Talk(Parcel in) {
        timeStamp = in.readLong();
        fromId = in.readInt();
        fromName = in.readString();
        fromUserHead = in.readString();
        toId = in.readInt();
        content = in.readString();
    }

    public static final Creator<Talk> CREATOR = new Creator<Talk>() {
        @Override
        public Talk createFromParcel(Parcel in) {
            return new Talk(in);
        }

        @Override
        public Talk[] newArray(int size) {
            return new Talk[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(timeStamp);
        dest.writeInt(fromId);
        dest.writeString(fromName);
        dest.writeString(fromUserHead);
        dest.writeInt(toId);
        dest.writeString(content);
    }
}
