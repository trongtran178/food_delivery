package hcmute.spkt.tranngoctrong.food_delivery.adapter;

import android.content.Context;
import android.content.Intent;
import android.location.Location;

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

import hcmute.spkt.tranngoctrong.food_delivery.FoodDeliveryApplication;
import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.RestaurantDetailsActivity;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.SearchRestaurantResultsActivity;

public class RestaurantResultAdapter extends RecyclerView.Adapter<RestaurantResultAdapter.RestaurantResultHolder> {

    private List<Restaurant> listRestaurantResult = new ArrayList<Restaurant>();
    private Context context;
    private FoodDeliveryApplication foodDeliveryApplication;

    public RestaurantResultAdapter(Context context) {
        this.context = context;
        foodDeliveryApplication = (FoodDeliveryApplication) this.context.getApplicationContext();
    }

    @NonNull
    @Override
    public RestaurantResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(context)
                .inflate(R.layout.item_restaurant_search_result,
                        parent, false);
        return new RestaurantResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantResultHolder holder, int position) {

        holder.nameTextView.setText(listRestaurantResult.get(position).getName());
        holder.addressTextView.setText(listRestaurantResult.get(position).getAddress());
        holder.restaurantTypeTextView.setText(listRestaurantResult.get(position).getType());

        Location restaurantLocation = new Location("restaurantProvider");

        restaurantLocation.setLatitude(listRestaurantResult.get(position).getLatitude());
        restaurantLocation.setLongitude(listRestaurantResult.get(position).getLongitude());

        holder.distanceFromUserTextView.setText(distanceFromUserString(restaurantLocation));
        Glide.with(holder.itemView)
                .load(listRestaurantResult.get(position).getAvatar())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.avatarImageView);
    }

    @Override
    public int getItemCount() {
        return listRestaurantResult != null ? listRestaurantResult.size() : 0;
    }

    public void setResults(List<Restaurant> restaurants) {
        this.listRestaurantResult = restaurants;
        notifyDataSetChanged();
    }


    class RestaurantResultHolder extends RecyclerView.ViewHolder {

        private ImageView avatarImageView;
        private TextView nameTextView;
        private TextView ratingNumberTextView;
        private TextView distanceFromUserTextView;
        private TextView addressTextView;
        private TextView restaurantTypeTextView;
        private TextView commentNumberTextView;
        private TextView takePictureNumberTextView;
        private TextView couponTextView;

        public RestaurantResultHolder(@NonNull final View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.restaurant_search_result_image_view);
            nameTextView = itemView.findViewById(R.id.restaurant_search_results_item_name_text_view);
            ratingNumberTextView = itemView.findViewById(R.id.restaurant_search_results_item_rating_number_text_view);
            distanceFromUserTextView = itemView.findViewById(R.id.restaurant_search_results_item_distance_to_me_text_view);
            addressTextView = itemView.findViewById(R.id.restaurant_search_results_item_address_text_view);
            restaurantTypeTextView = itemView.findViewById(R.id.restaurant_search_results_item_restaurant_type_text_view);
            commentNumberTextView = itemView.findViewById(R.id.restaurant_search_results_item_comment_number_text_view);
            takePictureNumberTextView = itemView.findViewById(R.id.restaurant_search_results_item_take_picture_number_text_view);
            couponTextView = itemView.findViewById(R.id.restaurant_search_results_item_coupon_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (context instanceof SearchRestaurantResultsActivity) {
                        ((SearchRestaurantResultsActivity) context).onHandleLoading(true);

                    }
                    Intent goToRestaurantDetail = new Intent(context, RestaurantDetailsActivity.class);
                    goToRestaurantDetail.putExtra("restaurant", listRestaurantResult.get(getLayoutPosition()));
                    context.startActivity(goToRestaurantDetail);
                }
            });
        }
    }

    private String distanceFromUserString(Location restaurantLocation) {
        long distance = (long) (foodDeliveryApplication.getUserLocation().distanceTo(restaurantLocation));
        if (distance < 1000) {
            System.out.println(String.format("%d", distance) + " m");
            return String.format("%d", distance) + "m";
        } else {
            System.out.println(String.format("%d", distance / 1000) + " km");
            return String.format("%d", distance / 1000) + " km";
        }
    }
}