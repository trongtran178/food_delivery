package hcmute.spkt.tranngoctrong.food_delivery.adapter;

import android.content.Context;
import android.content.Intent;
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

import hcmute.spkt.tranngoctrong.food_delivery.MainActivity;
import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.page.search.ChooseProvinceActivity;
import hcmute.spkt.tranngoctrong.food_delivery.page.search.SearchActivity;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantHolder> {

    private List<Restaurant> restaurants = new ArrayList<Restaurant>();
    private Context parentContext;

    public RestaurantAdapter(Context context) {
        this.parentContext = context;
    }

    @NonNull
    @Override
    public RestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, parent, false);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentContext, ChooseProvinceActivity.class);
                // startActivityForResult(intent, )
            }
        });


        //if you need three fix imageview in width
        return new RestaurantHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class RestaurantHolder extends RecyclerView.ViewHolder {

        public RestaurantHolder(@NonNull View itemView) {
            super(itemView);

        }

    }

}
