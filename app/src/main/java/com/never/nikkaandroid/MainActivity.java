package com.never.nikkaandroid;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.never.nikkaandroid.adpter.MainActivityAdpter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPage = (ViewPager) findViewById(R.id.viewpage);
        android.support.design.widget.TabLayout tabLayout = (android.support.design.widget.TabLayout) findViewById(R.id.tabLayout);

        viewPage.setAdapter(new MainActivityAdpter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPage);
    }
}
