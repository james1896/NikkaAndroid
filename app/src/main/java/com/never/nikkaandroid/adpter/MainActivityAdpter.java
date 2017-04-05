package com.never.nikkaandroid.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.never.nikkaandroid.grid.GridFragment;
import com.never.nikkaandroid.home.HomeFragment;
import com.never.nikkaandroid.me.MeFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivityAdpter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();

    public MainActivityAdpter(FragmentManager fm) {
        super(fm);
        this.fragments.add(new HomeFragment());
        this.fragments.add(new GridFragment());
        this.fragments.add(new MeFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return new String[]{"水果","格铺","我的"}[position];
    }
}
