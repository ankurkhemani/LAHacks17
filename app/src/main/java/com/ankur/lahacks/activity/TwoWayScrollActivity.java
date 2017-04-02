package com.ankur.lahacks.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.ankur.lahacks.R;
import com.ankur.lahacks.adapters.HorizontalPagerAdapter;
import com.ankur.lahacks.model.Item;
import com.ankur.lahacks.model.ResultHolder;
import com.ankur.lahacks.utils.ImageUploadService;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TwoWayScrollActivity extends AppCompatActivity {

    @BindView(R.id.heading)
    TextView heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizontal_pager);
        ButterKnife.bind(this);

        Bitmap bitmap = ResultHolder.getImage();
        if (bitmap == null) {
            finish();
            return;
        }

        //create a file to write bitmap data
        File f = new File(this.getCacheDir(), "upload.jpg");
        try {
            f.createNewFile();

            //Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();

            //write the bytes in file
            FileOutputStream fos = null;
            fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        uploadFile(f);


        // create dummy data
        final List<List<Item>> listItems = new ArrayList<>();
        for(int i=0; i<5; i++){
            final List<Item> items = new ArrayList<>();
            for(int j=0; j<3; j++){
                items.add(new Item(R.drawable.a, "You had me at Hello World"));
                items.add(new Item(R.drawable.b, "Fortune favors the brave"));
                items.add(new Item(R.drawable.b, "Manners maketh man"));
            }
            listItems.add(items);
        }

        final HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager =
                (HorizontalInfiniteCycleViewPager) findViewById(R.id.hicvp);
        horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(this, listItems));
        horizontalInfiniteCycleViewPager.setCurrentItem(listItems.size() - 1);

        horizontalInfiniteCycleViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                final int hzPosition  = horizontalInfiniteCycleViewPager.getRealItem();
                int size = listItems.size();
                if(hzPosition != size-1)
                    heading.setText(String.format("More Photos Clicked %d s ago", size - hzPosition -1));
                else {
                    heading.setText("More Photos Clicked In Time .. ");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void uploadFile(File file) {
        ImageUploadService service = new Retrofit.Builder().baseUrl("http://thumbyoga.azurewebsites.net/").build().create(ImageUploadService.class);

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "image_upload");

        retrofit2.Call<okhttp3.ResponseBody> req = service.upload(name, body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Do Something
                Log.d("Upload", "success: " + response.toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
