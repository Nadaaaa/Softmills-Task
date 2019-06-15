package com.softmills.softmillstask.NetworkUtils;

import com.softmills.softmillstask.models.Image;
import com.softmills.softmillstask.models.SearchResponseObject;
import com.softmills.softmillstask.models.UploadResponseObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface NetworkUtils {

    String contentType = "Content-Type: application/json";
    String langCode = "x-lang-code: en-us";
    String authToken = "x-auth-token: a3a982607f00d44117ef0172508860a1";
    String userType = "X-user-type: 1";

    String authTokenUpdate = "x-auth-token: e7cde7086fb857a39e5fb4e5567eecb0";
    String userTypeUpdate = "X-user-type: 0";


    @Headers({contentType, langCode, authToken, userType})
    @POST("photo/search")
    Call<SearchResponseObject> searchPhoto(@Body String keyword);

    @Headers({contentType, langCode, authTokenUpdate, userTypeUpdate})
    @POST("profile/upload")
    Call<UploadResponseObject> uploadPhoto(@Body Image image_profile);

}
