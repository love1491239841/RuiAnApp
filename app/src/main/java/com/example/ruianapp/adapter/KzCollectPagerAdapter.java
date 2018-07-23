package com.example.ruianapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import java.util.List;

/**
 * Created by zhanghx on 2018/5/16.
 */

public class KzCollectPagerAdapter extends android.support.v4.app.FragmentPagerAdapter{
    private List<Fragment>fragmentList;
    private String[] titleList;

    public KzCollectPagerAdapter(FragmentManager fm, List<Fragment> fragmentList,String[] titleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }
    @Override
    public CharSequence getPageTitle(int position) {

        return titleList[position];
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
