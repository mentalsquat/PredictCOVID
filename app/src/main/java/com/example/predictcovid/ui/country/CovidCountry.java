package com.example.predictcovid.ui.country;

import android.os.Parcel;
import android.os.Parcelable;

public class CovidCountry implements Parcelable {
    String mCovidCountry, mTodayCases, mDeaths, mTodayDeaths, mRecovered, mCritical, mTodayRecovered, mActive, mFlag;
    int mCases;

    public CovidCountry(String mCovidCountry, String mTodayCases, String mDeaths, String mTodayDeaths, String mRecovered, String mCritical, String mTodayRecovered, String mActive, String mFlag, int mCases) {
        this.mCovidCountry = mCovidCountry;
        this.mTodayCases = mTodayCases;
        this.mDeaths = mDeaths;
        this.mTodayDeaths = mTodayDeaths;
        this.mRecovered = mRecovered;
        this.mCritical = mCritical;
        this.mTodayRecovered = mTodayRecovered;
        this.mActive = mActive;
        this.mFlag = mFlag;
        this.mCases = mCases;
    }

    public String getmCovidCountry() {
        return mCovidCountry;
    }

    public String getmTodayCases() {
        return mTodayCases;
    }

    public String getmDeaths() {
        return mDeaths;
    }

    public String getmTodayDeaths() {
        return mTodayDeaths;
    }

    public String getmRecovered() {
        return mRecovered;
    }

    public String getmCritical() {
        return mCritical;
    }

    public String getmTodayRecovered() {
        return mTodayRecovered;
    }

    public String getmActive() {
        return mActive;
    }

    public String getmFlag() {
        return mFlag;
    }

    public int getmCases() {
        return mCases;
    }

    public void setmCovidCountry(String mCovidCountry) {
        this.mCovidCountry = mCovidCountry;
    }

    public void setmTodayCases(String mTodayCases) {
        this.mTodayCases = mTodayCases;
    }

    public void setmDeaths(String mDeaths) {
        this.mDeaths = mDeaths;
    }

    public void setmTodayDeaths(String mTodayDeaths) {
        this.mTodayDeaths = mTodayDeaths;
    }

    public void setmRecovered(String mRecovered) {
        this.mRecovered = mRecovered;
    }

    public void setmCritical(String mCritical) {
        this.mCritical = mCritical;
    }

    public void setmTodayRecovered(String mTodayRecovered) {
        this.mTodayRecovered = mTodayRecovered;
    }

    public void setmActive(String mActive) {
        this.mActive = mActive;
    }

    public void setmFlag(String mFlag) {
        this.mFlag = mFlag;
    }

    public void setmCases(int mCases) {
        this.mCases = mCases;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTodayCases);
        dest.writeString(this.mCovidCountry);
        dest.writeString(this.mDeaths);
        dest.writeString(this.mTodayDeaths);
        dest.writeString(this.mRecovered);
        dest.writeString(this.mCritical);
        dest.writeString(this.mTodayRecovered);
        dest.writeString(this.mActive);
        dest.writeString(this.mFlag);
        dest.writeInt(this.mCases);
    }

    protected CovidCountry(Parcel in) {
        this.mTodayCases = in.readString();
        this.mCovidCountry = in.readString();
        this.mDeaths = in.readString();
        this.mTodayDeaths = in.readString();
        this.mRecovered = in.readString();
        this.mCritical = in.readString();
        this.mTodayRecovered = in.readString();
        this.mActive = in.readString();
        this.mFlag = in.readString();
        this.mCases = in.readInt();
    }

    public static final Creator<CovidCountry> CREATOR = new Creator<CovidCountry>() {
        @Override
        public CovidCountry createFromParcel(Parcel source) {
            return new CovidCountry(source);
        }

        @Override
        public CovidCountry[] newArray(int size) {
            return new CovidCountry[size];
        }
    };
}
