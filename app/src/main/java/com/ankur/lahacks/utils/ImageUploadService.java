package com.ankur.lahacks.utils;

import com.ankur.lahacks.model.APIItem;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Ankur on 02/04/17.
 */

public interface ImageUploadService {
    @Multipart
    @POST("upload")
    Call<APIItem> upload(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file
    );
}