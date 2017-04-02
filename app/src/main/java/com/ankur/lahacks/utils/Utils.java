package com.ankur.lahacks.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ankur.lahacks.R;
import com.ankur.lahacks.model.Item;

public class Utils {

    public static void setupItem(final View view, final Item item) {
        final ImageView img = (ImageView) view.findViewById(R.id.image);
        img.setImageResource(item.getImageID());
        final TextView textView = (TextView) view.findViewById(R.id.text);
//        textView.setText(item.getTitle());
    }
}
