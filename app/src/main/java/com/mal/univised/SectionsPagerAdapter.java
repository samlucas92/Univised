package com.mal.univised;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by admin on 1/3/2017.
 */
public class SectionsPagerAdapter  extends FragmentPagerAdapter implements View.OnTouchListener {


    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Question 1";
            case 1:
                return "Question 2";
            case 2:
                return "Question 3";
            case 3:
                return "Question 4";
            case 4:
                return "Question 5";
            case 5:
                return "Question 6";
            case 6:
                return "Question 7";
            case 7:
                return "Question 8";

        }
        return null;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}


