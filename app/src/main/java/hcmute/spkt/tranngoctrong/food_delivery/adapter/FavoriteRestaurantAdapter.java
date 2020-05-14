package hcmute.spkt.tranngoctrong.food_delivery.adapter;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;

public class FavoriteRestaurantAdapter extends RecyclerView.Adapter<FavoriteRestaurantAdapter.FavoriteRestaurantHolder> {

    private List<Restaurant> favoriteRestaurant = new ArrayList<Restaurant>();
    private Context parentContext;

    public FavoriteRestaurantAdapter(Context context) {
        this.parentContext = context;
    }

    @NonNull
    @Override
    public FavoriteRestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_restaurant_item, parent, false);

        DisplayMetrics displaymetrics = new DisplayMetrics();

        //if you need three fix imageview in width
        return new FavoriteRestaurantHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteRestaurantHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class FavoriteRestaurantHolder extends RecyclerView.ViewHolder {
        private TextView testTemp;


        public FavoriteRestaurantHolder(@NonNull View itemView) {
            super(itemView);
            testTemp = itemView.findViewById(R.id.texttemp);
        }

    }

}
