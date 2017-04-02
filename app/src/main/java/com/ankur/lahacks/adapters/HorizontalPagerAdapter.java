package com.ankur.lahacks.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ankur.lahacks.R;
import com.gigamole.infinitecycleviewpager.VerticalInfiniteCycleViewPager;

import java.util.List;

public class HorizontalPagerAdapter extends PagerAdapter {

    private List<List<String>> listItems;

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public HorizontalPagerAdapter(final Context context, List<List<String>> listItems) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        view = mLayoutInflater.inflate(R.layout.vertical_pager, container, false);

        final VerticalInfiniteCycleViewPager verticalInfiniteCycleViewPager =
                (VerticalInfiniteCycleViewPager) view.findViewById(R.id.vicvp);
        verticalInfiniteCycleViewPager.setAdapter(
                new VerticalPagerAdapter(mContext, listItems.get(position))
        );
        verticalInfiniteCycleViewPager.setCurrentItem(listItems.get(position).size()/2);
        verticalInfiniteCycleViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("position: ", verticalInfiniteCycleViewPager.getRealItem() + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
