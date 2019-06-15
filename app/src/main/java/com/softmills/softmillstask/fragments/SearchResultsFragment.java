package com.softmills.softmillstask.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmills.softmillstask.NetworkUtils.Retrofit;
import com.softmills.softmillstask.R;
import com.softmills.softmillstask.adapters.ImagesAdapter;
import com.softmills.softmillstask.models.Image;
import com.softmills.softmillstask.models.SearchResponseObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softmills.softmillstask.Utils.Const.SEARCH_TEXT;
import static com.softmills.softmillstask.Utils.Const.URL_SEARCH;

public class SearchResultsFragment extends Fragment {

    static final String TAG = SearchResultsFragment.class.getName();

    @BindView(R.id.rv_images)
    RecyclerView rv_images;

    public static SearchResultsFragment newInstance(String searchText) {
        SearchResultsFragment searchResultsFragment = new SearchResultsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_TEXT, searchText);
        searchResultsFragment.setArguments(bundle);
        return searchResultsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_results, container, false);

        ButterKnife.bind(this, rootView);

        String search_text = getArguments().getString(SEARCH_TEXT);
        final List<Image> imagesList = new ArrayList<>();

        final ImagesAdapter imagesAdapter = new ImagesAdapter(getContext(), imagesList);
        rv_images.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_images.setHasFixedSize(false);
        rv_images.setAdapter(imagesAdapter);

        Retrofit.getService(URL_SEARCH).searchPhoto(search_text).enqueue(new Callback<SearchResponseObject>() {
            @Override
            public void onResponse(Call<SearchResponseObject> call, Response<SearchResponseObject> response) {
                imagesList.addAll(response.body().getData().getData());
                imagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SearchResponseObject> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return rootView;
    }
}
