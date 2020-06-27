package hcmute.spkt.tranngoctrong.food_delivery.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;

public class RestaurantImageAdapter extends RecyclerView.Adapter<RestaurantImageAdapter.RestaurantImageHolder> {

    private Context context;
    private List<String> restaurantImagesUrl;
    private LayoutInflater layoutInflater;


    public RestaurantImageAdapter(Context context, List<String> restaurantImagesUrl) {
        this.context = context;
        this.restaurantImagesUrl = restaurantImagesUrl;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RestaurantImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_restaurant_image, parent, false);
        return new RestaurantImageAdapter.RestaurantImageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantImageHolder holder, int position) {
        Glide.with(holder.restaurantImageVIew).load(restaurantImagesUrl.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.restaurantImageVIew);
    }

    @Override
    public int getItemCount() {
        return restaurantImagesUrl.size();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    class RestaurantImageHolder extends RecyclerView.ViewHolder {

        ImageView restaurantImageVIew;

        RestaurantImageHolder(View itemView) {
            super(itemView);

            restaurantImageVIew = itemView.findViewById(R.id.item_restaurant_image);
        }
    }
}
