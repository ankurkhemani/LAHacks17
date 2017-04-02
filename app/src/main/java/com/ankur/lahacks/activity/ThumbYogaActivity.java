package com.ankur.lahacks.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ankur.lahacks.R;
import com.ankur.lahacks.adapters.HorizontalPagerAdapter;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

public class ThumbYogaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizontal_pager);

        final HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager =
                (HorizontalInfiniteCycleViewPager) findViewById(R.id.hicvp);
        horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(this));
    }
}
