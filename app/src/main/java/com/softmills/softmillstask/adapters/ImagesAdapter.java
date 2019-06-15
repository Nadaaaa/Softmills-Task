package com.softmills.softmillstask.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.softmills.softmillstask.R;
import com.softmills.softmillstask.models.Image;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageViewHolder> {
    private Context context;
    private List<Image> imagesList;

    public ImagesAdapter(Context context, List<Image> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layoutRepositoryItem = R.layout.item_image;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutRepositoryItem, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imagesViewHolder, int i) {
        imagesViewHolder.bind(imagesList.get(i));
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemImage_image)
        ImageView imageView;

        ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Image image) {
            Glide.with(context).load(image.getUrl()).into(imageView);
        }
    }
}


