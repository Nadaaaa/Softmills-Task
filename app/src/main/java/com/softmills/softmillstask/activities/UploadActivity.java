package com.softmills.softmillstask.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.softmills.softmillstask.NetworkUtils.Retrofit;
import com.softmills.softmillstask.R;
import com.softmills.softmillstask.models.Image;
import com.softmills.softmillstask.models.UploadResponseObject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softmills.softmillstask.Utils.Const.URL_UPLOAD;

public class UploadActivity extends AppCompatActivity {

    final static String TAG = UploadActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.upload_button)
    void upload() {
        Image image = new Image();

        Uri path = Uri.parse("drawable://" + R.drawable.pic_upload);
        image.setUrl(path.toString());
        
        Retrofit.getService(URL_UPLOAD).uploadPhoto(image).enqueue(new Callback<UploadResponseObject>() {
            @Override
            public void onResponse(Call<UploadResponseObject> call, Response<UploadResponseObject> response) {
            }

            @Override
            public void onFailure(Call<UploadResponseObject> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @OnClick(R.id.upload_button_skip)
    void skip() {
        UploadActivity.this.finish();
    }
}
