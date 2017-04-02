package com.ankur.lahacks.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ankur.lahacks.R;
import com.ankur.lahacks.utils.Utils;

import static com.ankur.lahacks.utils.Utils.setupItem;

public class VerticalPagerAdapter extends PagerAdapter {

    private final Utils.LibraryObject[] TWO_WAY_LIBRARIES = new Utils.LibraryObject[]{
            new Utils.LibraryObject(
                    R.drawable.f,
                    "You had me at Hello World"
            ),
            new Utils.LibraryObject(
                    R.drawable.f,
                    "Fortune favors the brave"
            ),
            new Utils.LibraryObject(
                    R.drawable.f,
                    "Manners maketh man"
            )
    };

    private LayoutInflater mLayoutInflater;

    public VerticalPagerAdapter(final Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return TWO_WAY_LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = mLayoutInflater.inflate(R.layout.item, container, false);

        setupItem(view, TWO_WAY_LIBRARIES[position]);

        final CardView cardView = (CardView) view.findViewById(R.id.cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "testing", Toast.LENGTH_SHORT);
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
