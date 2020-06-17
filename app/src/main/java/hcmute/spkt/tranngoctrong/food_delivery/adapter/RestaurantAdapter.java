package hcmute.spkt.tranngoctrong.food_delivery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantHolder> {

    private List<Restaurant> restaurants = new ArrayList<Restaurant>();
    private Context context;

    public RestaurantAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantHolder holder, int position) {
        holder.searchRestaurantTitleTextView.setText(restaurants.get(position).getName());
        holder.searchRestaurantSubtitleTextView.setText(restaurants.get(position).getDescription());
        Glide.with(holder.itemView)
                .load(restaurants.get(position).getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.searchRestaurantImageView);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void setResults(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        notifyDataSetChanged();
    }

    class RestaurantHolder extends RecyclerView.ViewHolder {

        private TextView searchRestaurantTitleTextView;
        private TextView searchRestaurantSubtitleTextView;
        private ImageView searchRestaurantImageView;

        public RestaurantHolder(@NonNull View itemView) {
            super(itemView);
            searchRestaurantTitleTextView = (TextView) itemView.findViewById(R.id.search_restaurant_title_text_view);
            searchRestaurantSubtitleTextView = (TextView) itemView.findViewById(R.id.search_restaurant_subtitle_text_view);
            searchRestaurantImageView = (ImageView) itemView.findViewById(R.id.search_restaurant_image_view);
        }
    }

}
