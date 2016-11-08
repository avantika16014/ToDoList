package com.example.admin.todolist;

/**
 * Created by Admin on 11/3/2016.
 */
import android.os.Parcelable;
import android.os.Parcel;
public class ListItem implements Parcelable{
    private String item_name;
    private String desc;

    public ListItem(){}

    public ListItem(Parcel list)
    {
        item_name=list.readString();
        desc=list.readString();
    }
    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_name);
        dest.writeString(desc);
    }

    public static final Parcelable.Creator<ListItem> CREATOR=new Parcelable.Creator<ListItem>(){

        @Override
        public ListItem createFromParcel(Parcel source) {
            return new ListItem(source);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };
}
