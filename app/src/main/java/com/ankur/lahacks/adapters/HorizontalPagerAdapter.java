package com.ankur.lahacks.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ankur.lahacks.R;
import com.ankur.lahacks.utils.Utils;
import com.gigamole.infinitecycleviewpager.VerticalInfiniteCycleViewPager;

public class HorizontalPagerAdapter extends PagerAdapter {

    private final Utils.LibraryObject[] LIBRARIES = new Utils.LibraryObject[]{
            new Utils.LibraryObject(
                    R.drawable.a,
                    "0:00"
            ),
            new Utils.LibraryObject(
                    R.drawable.b,
                    "0:01"
            ),
            new Utils.LibraryObject(
                    R.drawable.c,
                    "0:02"
            ),
            new Utils.LibraryObject(
                    R.drawable.d,
                    "0:03"
            ),
            new Utils.LibraryObject(
                    R.drawable.e,
                    "0:04"
            )
    };

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public HorizontalPagerAdapter(final Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        view = mLayoutInflater.inflate(R.layout.vertical_pager, container, false);

        final TextView time = (TextView) view.findViewById(R.id.time);
        time.setText(LIBRARIES[position].getTitle());

        final VerticalInfiniteCycleViewPager verticalInfiniteCycleViewPager =
                (VerticalInfiniteCycleViewPager) view.findViewById(R.id.vicvp);
        verticalInfiniteCycleViewPager.setAdapter(
                new VerticalPagerAdapter(mContext)
        );
        verticalInfiniteCycleViewPager.setCurrentItem(position);

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
