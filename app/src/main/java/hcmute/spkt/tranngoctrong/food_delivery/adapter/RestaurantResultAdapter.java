package hcmute.spkt.tranngoctrong.food_delivery.adapter;

import android.content.Context;
import android.content.Intent;
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
import hcmute.spkt.tranngoctrong.food_delivery.views.search.RestaurantDetailsActivity;

public class RestaurantResultAdapter extends RecyclerView.Adapter<RestaurantResultAdapter.RestaurantResultHolder> {

    private List<Restaurant> listRestaurantResult = new ArrayList<Restaurant>();
    private Context context;

    public RestaurantResultAdapter(Context context) {
        this.context = context;
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

        Glide.with(holder.itemView)
                .load(listRestaurantResult.get(position).getAvatar())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.avatarImageView);
    }

    @Override
    public int getItemCount() {
        return listRestaurantResult.size();
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
            avatarImageView = (ImageView) itemView.findViewById(R.id.restaurant_search_result_image_view);
            nameTextView = (TextView) itemView.findViewById(R.id.restaurant_search_results_item_name_text_view);
            ratingNumberTextView = (TextView) itemView.findViewById(R.id.restaurant_search_results_item_rating_number_text_view);
            distanceFromUserTextView = (TextView) itemView.findViewById(R.id.restaurant_detail_distance_from_user_text_view);
            addressTextView = (TextView) itemView.findViewById(R.id.restaurant_search_results_item_address_text_view);
            restaurantTypeTextView = (TextView) itemView.findViewById(R.id.restaurant_search_results_item_restaurant_type_text_view);
            commentNumberTextView = (TextView) itemView.findViewById(R.id.restaurant_search_results_item_comment_number_text_view);
            takePictureNumberTextView = (TextView) itemView.findViewById(R.id.restaurant_search_results_item_take_picture_number_text_view);
            couponTextView = (TextView) itemView.findViewById(R.id.restaurant_search_results_item_coupon_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToRestaurantDetail = new Intent(context, RestaurantDetailsActivity.class);
                    goToRestaurantDetail.putExtra("restaurant", listRestaurantResult.get(getLayoutPosition()));
                    context.startActivity(goToRestaurantDetail);
                }
            });
        }


    }
}
