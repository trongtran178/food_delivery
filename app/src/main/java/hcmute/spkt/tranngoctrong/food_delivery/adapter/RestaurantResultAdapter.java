package hcmute.spkt.tranngoctrong.food_delivery.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;

public class RestaurantResultAdapter extends RecyclerView.Adapter<RestaurantResultAdapter.RestaurantResultHolder> {

    private List<Restaurant> listRestaurantResult = new ArrayList<Restaurant>();
    private Context context;

    public RestaurantResultAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.restaurant_search_result_item, parent, false);

        return new RestaurantResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantResultHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class RestaurantResultHolder extends RecyclerView.ViewHolder {

        public RestaurantResultHolder(@NonNull View itemView) {
            super(itemView);

        }

    }
}
