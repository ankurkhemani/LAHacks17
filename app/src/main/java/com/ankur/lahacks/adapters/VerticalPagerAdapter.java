package com.ankur.lahacks.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ankur.lahacks.R;
import com.ankur.lahacks.model.Item;

import java.util.List;

import static com.ankur.lahacks.utils.Utils.setupItem;

public class VerticalPagerAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private List<Item> items;

    public VerticalPagerAdapter(final Context context, List<Item> items) {
        mLayoutInflater = LayoutInflater.from(context);
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = mLayoutInflater.inflate(R.layout.item, container, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "testing", Toast.LENGTH_SHORT);
            }
        });

        setupItem(view, items.get(position));
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
