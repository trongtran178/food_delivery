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
import hcmute.spkt.tranngoctrong.food_delivery.model.Food;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder> {

    private List<Food> restaurantFoods = new ArrayList<Food>();

    private Context context;

    public FoodAdapter(Context context, List<Food> restaurantFoods) {
        this.context = context;
        this.restaurantFoods = restaurantFoods;
    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new FoodHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class FoodHolder extends RecyclerView.ViewHolder {
        public FoodHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
