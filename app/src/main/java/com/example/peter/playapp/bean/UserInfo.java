package com.example.peter.playapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo implements Parcelable {
    private String account;
    private String password;
    private String genkey;
    private String deviceId;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGenkey() {
        return genkey;
    }

    public void setGenkey(String genkey) {
        this.genkey = genkey;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.account);
        dest.writeString(this.password);
        dest.writeString(this.genkey);
        dest.writeString(this.deviceId);
    }

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        this.account = in.readString();
        this.password = in.readString();
        this.genkey = in.readString();
        this.deviceId = in.readString();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
