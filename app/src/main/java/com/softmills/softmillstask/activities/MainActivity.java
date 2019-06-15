package com.softmills.softmillstask.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.softmills.softmillstask.R;
import com.softmills.softmillstask.fragments.EmptyPageFragment;
import com.softmills.softmillstask.fragments.SearchResultsFragment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_editText_search)
    EditText editText_search;

    String search_text;
    Timer timer;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();

        replaceToEmptyFragment();

        search();
    }

    void search() {
        editText_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (!editText_search.getText().toString().isEmpty()) {
                            search_text = editText_search.getText().toString();
                            replaceToResultFragment();
                        }
                    }
                }, 5000);

            }
        });
    }

    public void replaceToEmptyFragment() {
        EmptyPageFragment emptyPageFragment = new EmptyPageFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.main_frameLayout, emptyPageFragment)
                .commit();

    }

    void replaceToResultFragment() {
        SearchResultsFragment searchResultsFragment = SearchResultsFragment.newInstance(search_text);
        fragmentManager.beginTransaction()
                .replace(R.id.main_frameLayout, searchResultsFragment)
                .commit();
    }

    @OnClick(R.id.main_fab_upload)
    void goToUploadActivity() {
        startActivity(new Intent(MainActivity.this, UploadActivity.class));
    }

   /* private String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageByteArray, Base64.DEFAULT);
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 11);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            Uri path = data.getData();
        }

    }*/
}
