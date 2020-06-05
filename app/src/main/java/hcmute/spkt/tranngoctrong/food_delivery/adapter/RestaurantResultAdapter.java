package hcmute.spkt.tranngoctrong.food_delivery.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.RestaurantDetailsActivity;

public class RestaurantResultAdapter extends RecyclerView.Adapter<RestaurantResultAdapter.RestaurantResultHolder> {

    private List<Restaurant> listRestaurantResult = new ArrayList<Restaurant>();
    private Context context;

    public RestaurantResultAdapter(Context context, List<Restaurant> listRestaurantResult) {
        this.context = context;
        this.listRestaurantResult = listRestaurantResult;
    }

    @NonNull
    @Override
    public RestaurantResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.restaurant_search_result_item, //
                                                             parent, false);

        return new RestaurantResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantResultHolder holder, int position) {
//        holder.restaurantSearchResultsItemNameTextView.setText(listRestaurantResult.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class RestaurantResultHolder extends RecyclerView.ViewHolder {

        // private TextView restaurant_search_results_item_name

        private TextView restaurantSearchResultsItemNameTextView;

        public RestaurantResultHolder(@NonNull View itemView) {
            super(itemView);
            restaurantSearchResultsItemNameTextView = (TextView) itemView.findViewById(R.id.restaurant_search_results_item_name_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToRestaurantDetail = new Intent(context, RestaurantDetailsActivity.class);
                    context.startActivity(goToRestaurantDetail);
                }
            });
        }


    }
}
