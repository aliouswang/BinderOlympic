package com.aliouswang.im.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Talk implements Parcelable {

    public long timeStamp;
    public String fromId;
    public String fromName;
    public String fromUserHead;
    public String toId;

    public String content;

    public Talk() {

    }

    protected Talk(Parcel in) {
        timeStamp = in.readLong();
        fromId = in.readString();
        fromName = in.readString();
        fromUserHead = in.readString();
        toId = in.readString();
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
        dest.writeString(fromId);
        dest.writeString(fromName);
        dest.writeString(fromUserHead);
        dest.writeString(toId);
        dest.writeString(content);
    }

    public void readFromParcel(Parcel dest) {
        this.timeStamp = dest.readLong();
        this.fromId = dest.readString();
        this.fromName = dest.readString();
        this.fromUserHead = dest.readString();
        this.toId = dest.readString();
        this.content = dest.readString();
    }

}
