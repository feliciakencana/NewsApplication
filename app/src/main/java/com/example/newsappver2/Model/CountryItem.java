package com.example.newsappver2.Model;

public class CountryItem {
    private String mCountryName;
    private int mFlagImage;

    public CountryItem(int flagImage) {
        mFlagImage = flagImage;
    }


    public int getFlagImage() {
        return mFlagImage;
    }
}
