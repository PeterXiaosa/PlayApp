package com.example.peter.playapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactInfoRequest implements Parcelable {
    private String phone;
    private int action;
    private String contactName;
    private String contactPhone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.phone);
        dest.writeInt(this.action);
        dest.writeString(this.contactName);
        dest.writeString(this.contactPhone);
    }

    public ContactInfoRequest() {
    }

    protected ContactInfoRequest(Parcel in) {
        this.phone = in.readString();
        this.action = in.readInt();
        this.contactName = in.readString();
        this.contactPhone = in.readString();
    }

    public static final Parcelable.Creator<ContactInfoRequest> CREATOR = new Parcelable.Creator<ContactInfoRequest>() {
        @Override
        public ContactInfoRequest createFromParcel(Parcel source) {
            return new ContactInfoRequest(source);
        }

        @Override
        public ContactInfoRequest[] newArray(int size) {
            return new ContactInfoRequest[size];
        }
    };
}
