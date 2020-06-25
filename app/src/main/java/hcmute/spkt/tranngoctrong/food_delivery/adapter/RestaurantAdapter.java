package hcmute.spkt.tranngoctrong.food_delivery.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.FoodDeliveryApplication;
import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.utils.OnLoadMoreListener;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.RestaurantDetailsActivity;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.SearchRestaurantActivity;

public class RestaurantAdapter extends RecyclerView.Adapter {

    private List<Restaurant> restaurants = new ArrayList<Restaurant>();
    private Context context;

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROGRESS = 0;
    private int visibleThreshold = 3;
    private int lastVisibleRow, totalRowCount;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean loading;


    public RestaurantAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;

        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView
                    .getLayoutManager();

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView,
                                       int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalRowCount = gridLayoutManager.getItemCount() / 2;
                    lastVisibleRow = gridLayoutManager
                            .findLastVisibleItemPosition() / 2;

                    System.out.println(totalRowCount);
                    System.out.println(lastVisibleRow + visibleThreshold);

                    if (!loading && totalRowCount <= (lastVisibleRow + visibleThreshold)) {
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == VIEW_ITEM) {
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_restaurant, parent, false);
            viewHolder = new RestaurantViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.data_placeholder_layout, parent, false);
            viewHolder = new ProgressBarViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof RestaurantViewHolder) {
            ((RestaurantViewHolder) holder).searchRestaurantTitleTextView.setText(restaurants.get(position).getName());
            ((RestaurantViewHolder) holder).searchRestaurantSubtitleTextView.setText(restaurants.get(position).getDescription());
            Glide.with(holder.itemView)
                    .load(restaurants.get(position).getAvatar())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(((RestaurantViewHolder) holder).searchRestaurantImageView);
        } else {
            ((ProgressBarViewHolder) holder).progressBar.setVisibility(View.VISIBLE);
            ((ProgressBarViewHolder) holder).shimmerViewContainer.startShimmerAnimation();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return restaurants.get(position) != null ? VIEW_ITEM : VIEW_PROGRESS;
    }

    @Override
    public int getItemCount() {
        return restaurants != null ? restaurants.size() : 0;
    }

    public void setResults(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        notifyDataSetChanged();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder {

        private TextView searchRestaurantTitleTextView;
        private TextView searchRestaurantSubtitleTextView;
        private ImageView searchRestaurantImageView;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            searchRestaurantTitleTextView = (TextView) itemView.findViewById(R.id.search_restaurant_title_text_view);
            searchRestaurantSubtitleTextView = (TextView) itemView.findViewById(R.id.search_restaurant_subtitle_text_view);
            searchRestaurantImageView = (ImageView) itemView.findViewById(R.id.search_restaurant_image_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((FoodDeliveryApplication) context.getApplicationContext()).getUserLocation() == null)
                        return;
                    if (context instanceof SearchRestaurantActivity) {
                        ((SearchRestaurantActivity) context).onHandleLoading(true);
                    }
                    Intent goToRestaurantDetail = new Intent(context, RestaurantDetailsActivity.class);
                    goToRestaurantDetail.putExtra("restaurant", restaurants.get(getLayoutPosition()));
                    context.startActivity(goToRestaurantDetail);
                }
            });
        }
    }

    public void setLoaded() {
        loading = false;
    }

    class ProgressBarViewHolder extends RecyclerView.ViewHolder {

        LinearLayout progressBar;
        ShimmerFrameLayout shimmerViewContainer;

        public ProgressBarViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.data_place_holder_layout);
            shimmerViewContainer = itemView.findViewById(R.id.shimmer_view_container);
        }
    }

}
