package com.ankur.lahacks.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.ankur.lahacks.R;
import com.ankur.lahacks.model.ResultHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreviewActivity extends Activity {

    @BindView(R.id.image)
    ImageView imageView;

    @OnClick(R.id.scroll)
    public void scroll(){
        Intent intent = new Intent(getApplicationContext(), TwoWayScrollActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        ButterKnife.bind(this);

        Bitmap bitmap = ResultHolder.getImage();
        if (bitmap == null) {
            finish();
            return;
        }

        // need to rotate back to portrait, Samsung issue
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        imageView.setImageBitmap(rotatedBitmap);

    }
}