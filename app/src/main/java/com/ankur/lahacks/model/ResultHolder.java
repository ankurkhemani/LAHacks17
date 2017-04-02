package com.ankur.lahacks.model;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

public class ResultHolder {

    private static WeakReference<Bitmap> image;


    public static void setImage(@Nullable Bitmap image) {
        ResultHolder.image = image != null ? new WeakReference<>(image) : null;
    }

    @Nullable
    public static Bitmap getImage() {
        return image != null ? image.get() : null;
    }

    public static void dispose() {
        setImage(null);
    }

}