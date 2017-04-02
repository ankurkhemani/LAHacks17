package com.ankur.lahacks.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ankur.lahacks.R;

public class Utils {

    public static void setupItem(final View view, final LibraryObject libraryObject) {
        final ImageView img = (ImageView) view.findViewById(R.id.image);
        img.setImageResource(libraryObject.getRes());
        final TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(libraryObject.getTitle());
    }

    public static class LibraryObject {

        private String mTitle;
        private int mRes;

        public LibraryObject(final int res, final String title) {
            mRes = res;
            mTitle = title;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(final String title) {
            mTitle = title;
        }

        public int getRes() {
            return mRes;
        }

        public void setRes(final int res) {
            mRes = res;
        }
    }
}
