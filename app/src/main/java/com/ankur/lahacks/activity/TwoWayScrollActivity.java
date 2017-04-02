package com.ankur.lahacks.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ankur.lahacks.R;
import com.ankur.lahacks.adapters.HorizontalPagerAdapter;
import com.ankur.lahacks.adapters.QuotesAdapter;
import com.ankur.lahacks.model.APIItem;
import com.ankur.lahacks.model.ResultHolder;
import com.ankur.lahacks.utils.ImageUploadService;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TwoWayScrollActivity extends AppCompatActivity {

    @BindView(R.id.heading)
    TextView heading;

//    @BindView(R.id.text)
//    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizontal_pager);
        ButterKnife.bind(this);

        Typeface roboto = Typeface.createFromAsset(getAssets(), "font/Roboto-Medium.ttf");
        heading.setTypeface(roboto);
//        text.setTypeface(roboto);

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


        // API call
        uploadFile(f);
    }

    private void uploadFile(File file) {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        ImageUploadService service = new Retrofit.Builder().baseUrl("http://thumbyoga.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build().create(ImageUploadService.class);

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "image_upload");

        Call<APIItem> req = service.upload(name, body);

        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Take it easy Hackers...");
        mProgressDialog.show();
        req.enqueue(new Callback<APIItem>() {
            @Override
            public void onResponse(Call<APIItem> call, Response<APIItem> response) {
                List<List<String>> items = response.body().getItems();
                List<String> quotes = response.body().getQuotes();

                Log.d("Upload", "success: " + response.toString());
                Log.d("Upload", "items: " + items);
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                setUpView(items, quotes);
            }

            @Override
            public void onFailure(Call<APIItem> call, Throwable t) {
                Log.d("Upload", "fail");
                t.printStackTrace();
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                heading.setVisibility(View.VISIBLE);
                heading.setText("Is it the UCLA Wifi or our server?");
            }
        });
    }

    private void setUpView(final List<List<String>> items, final List<String> quotes){
        // create dummy data
//        final List<List<Item>> listItems = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            final List<Item> items = new ArrayList<>();
//            for (int j = 0; j < 3; j++) {
//                items.add(new Item(R.drawable.a, "You had me at Hello World"));
//                items.add(new Item(R.drawable.b, "Fortune favors the brave"));
//                items.add(new Item(R.drawable.b, "Manners maketh man"));
//            }
//            listItems.add(items);
//        }

        heading.setVisibility(View.VISIBLE);
//        text.setVisibility(View.VISIBLE);
//        if(quotes!=null)
//            text.setText(quotes.get(0));

        final HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager =
                (HorizontalInfiniteCycleViewPager) findViewById(R.id.hicvp);
        horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(this, items));
        horizontalInfiniteCycleViewPager.setCurrentItem(items.size() - 1);

        horizontalInfiniteCycleViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                final int hzPosition = horizontalInfiniteCycleViewPager.getRealItem();
                int size = items.size();
                if (hzPosition != size - 1)
                    heading.setText(String.format("Photos clicked %d s ago", size - hzPosition - 1));
                else {
                    heading.setText("Photos clicked now");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Specify a layout for RecyclerView
        // Create a horizontal RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        );
        mRecyclerView.setLayoutManager(mLayoutManager);


//        dummy_quotes.add("You had me at Hello World!");
//        dummy_quotes.add("If you are born poor it's not your fault, but if your die poor it's your fault");


        // Initialize a new Adapter for RecyclerView
        if(quotes!=null) {
            QuotesAdapter quotesAdapter = new QuotesAdapter(this, quotes);

            // Set an adapter for RecyclerView
            mRecyclerView.setAdapter(quotesAdapter);
        }
    }
}
