package com.example.ruianapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by zhanghx on 2018/5/16.
 */

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter{
    private List<Fragment>fragmentList;

    public FragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
