package com.aliouswang.im.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public String id;
    public String name;
    public String head_url;

    public User(String id, String name, String head_url) {
        this.id = id;
        this.name = name;
        this.head_url = head_url;
    }

    protected User(Parcel in) {
        id = in.readString();
        name = in.readString();
        head_url = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(head_url);
    }

    public void readFromParcel(Parcel dest) {
        this.id = dest.readString();
        this.name = dest.readString();
        this.head_url = dest.readString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + head_url.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
