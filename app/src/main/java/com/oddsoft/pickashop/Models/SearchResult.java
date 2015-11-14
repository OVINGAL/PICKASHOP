package com.oddsoft.pickashop.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by afsal on 14/11/15.
 */
public class SearchResult implements Parcelable {

    public static final Parcelable.Creator<SearchResult> CREATOR = new Parcelable.Creator<SearchResult>() {
        @Override
        public SearchResult createFromParcel(Parcel source) {
            return new SearchResult(source);
        }

        @Override
        public SearchResult[] newArray(int size) {
            return new SearchResult[size];
        }
    };
    public String company_name;
    public String company_id;
    public String company_city;
    public String company_phone1;
    public String company_phone2;
    public String company_type;
    public String company_image;

    public SearchResult() {
    }

    private SearchResult(Parcel in) {

        this.company_name = in.readString();
        this.company_id = in.readString();
        this.company_type = in.readString();
        this.company_phone1 = in.readString();
        this.company_phone2 = in.readString();
        this.company_image = in.readString();
        this.company_city = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(company_name);
        parcel.writeString(company_id);
        parcel.writeString(company_type);
        parcel.writeString(company_phone1);
        parcel.writeString(company_phone2);
        parcel.writeString(company_image);
        parcel.writeString(company_city);
    }


}
