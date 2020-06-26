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
import hcmute.spkt.tranngoctrong.food_delivery.model.FoodCategory;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder> {

    private Context context;
    private List<FoodCategory> restaurantFoodCategories = new ArrayList<FoodCategory>();

    public FoodAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new FoodHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {
        holder.foodTitleView.setText(restaurantFoodCategories.get(position).getFoods().get(0).getName());
        Glide.with(holder.itemView)
                .load(restaurantFoodCategories.get(position).getFoods().get(0).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.foodAvatarImageView);
    }

    @Override
    public int getItemCount() {
        return this.restaurantFoodCategories == null ? 0 : this.restaurantFoodCategories.size();
    }

    class FoodHolder extends RecyclerView.ViewHolder {

        private ImageView foodAvatarImageView;
        private TextView foodTitleView;

        public FoodHolder(@NonNull View itemView) {
            super(itemView);
            foodAvatarImageView = itemView.findViewById(R.id.item_food_avatar);
            foodTitleView = itemView.findViewById(R.id.item_food_title);
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setRestaurantFoodCategories(List<FoodCategory> foodCategories) {
        this.restaurantFoodCategories = foodCategories;
        notifyDataSetChanged();
    }
}
