package com.example.newsappver2.Adapter;

import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.newsappver2.Fragment.BusinessFragment;
import com.example.newsappver2.Fragment.EntertainmentFragment;
import com.example.newsappver2.Fragment.HealthFragment;
import com.example.newsappver2.Fragment.ScienceFragment;
import com.example.newsappver2.Fragment.SportFragment;
import com.example.newsappver2.Fragment.TechnologyFragment;
import com.example.newsappver2.Fragment.TrendingFragment;
import com.example.newsappver2.MainActivity;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        //business entertainment general health science sports technology
        switch(position){
            case 0:
                fragment=new TrendingFragment();

                break;
            case 1:
                fragment=new BusinessFragment();
                break;
            case 2:
                fragment=new EntertainmentFragment();
                break;
            case 3:
                fragment=new HealthFragment();
                break;
            case 4:
                fragment=new ScienceFragment();
                break;
            case 5:
                fragment=new SportFragment();
                break;
            case 6:
                fragment=new TechnologyFragment();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        //business entertainment general health science sports technology
        switch(position){
            case 0:
                title="Trending";
                break;
            case 1:
                title="Business";
                break;
            case 2:
                title="Entertainment";
                break;
            case 3:
                title="Health";
                break;
            case 4:
                title="Science";
                break;
            case 5:
                title="Sports";
                break;
            case 6:
                title="Technology";
                break;
        }

        return title;
    }
}
