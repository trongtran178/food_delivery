package hcmute.spkt.tranngoctrong.food_delivery.views.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.model.Province;
import hcmute.spkt.tranngoctrong.food_delivery.utils.OnFoodDeliveryApplicationLoading;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.search_results_fragment.CommonResults;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.search_results_fragment.Filters;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.search_results_fragment.MostRightResults;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.search_results_fragment.NearMeResults;

public class SearchRestaurantResultsActivity extends AppCompatActivity implements OnFoodDeliveryApplicationLoading {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SearchView searchRestaurantResultsView;
    private ImageButton search_results_back_button;
    private TextView searchRestaurantResultsProvinceTextView;
    private LinearLayout searchRestaurantResultsLoadingLayout;
    private String searchQuery;
    private String provinceSearch;
    private String provinceSlugSearch;
    private Handler handler;
    private static final String SEARCH_QUERY_EXTRA = "SEARCH_QUERY_EXTRA";
    private static final String SEARCH_PROVINCE_EXTRA = "SEARCH_PROVINCE_EXTRA";
    private static final String SEARCH_PROVINCE_SLUG_EXTRA = "SEARCH_PROVINCE_SLUG_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurant_results);

        searchRestaurantResultsView = findViewById(R.id.search_restaurant_results_view);
        tabLayout = findViewById(R.id.search_results_tab);
        viewPager = findViewById(R.id.search_results_container);
        search_results_back_button = findViewById(R.id.search_results_back_button);
        searchRestaurantResultsProvinceTextView = findViewById(R.id.search_restaurant_results_province);
        searchRestaurantResultsLoadingLayout = findViewById(R.id.search_restaurant_results_loading_layout);

        Intent intent = getIntent();
        provinceSearch = intent.getStringExtra(SEARCH_PROVINCE_EXTRA);
        provinceSlugSearch = intent.getStringExtra(SEARCH_PROVINCE_SLUG_EXTRA);

        searchQuery = intent.getStringExtra(SEARCH_QUERY_EXTRA);
        searchRestaurantResultsView.setQuery(searchQuery, true);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        searchRestaurantResultsView.setOnQueryTextListener(searchViewQueryTextListener);
        search_results_back_button.setOnClickListener(searchResultsBackButtonClickListener);


        handler = new Handler();

        searchRestaurantResultsProvinceTextView.setText(Html.fromHtml(
                "<b> Địa điểm </b> ở <b> " + provinceSearch + "</b>"
        ));
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager(), SectionsPageAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new MostRightResults(searchQuery, new Province(provinceSearch, provinceSlugSearch, false)), "Đúng nhất");
        adapter.addFragment(new NearMeResults(searchQuery, new Province(provinceSearch, provinceSlugSearch, false)), "Gần tôi");

        adapter.addFragment(new CommonResults(), "Phổ biến");
        adapter.addFragment(new Filters(), "Bộ lọc");
        viewPager.setAdapter(adapter);
    }

    private View.OnClickListener searchResultsBackButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SearchRestaurantResultsActivity.super.finish();
        }
    };

    private SearchView.OnQueryTextListener searchViewQueryTextListener = new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextSubmit(String query) {
            switch (tabLayout.getSelectedTabPosition()) {
                // MostRight tab
                case 0: {
                    MostRightResults mostRightResults = (MostRightResults) getSupportFragmentManager().getFragments().get(0);
                    mostRightResults.setKeyword(query);
                    mostRightResults.refreshDataWithNewKeywordSearch();
                }
                // NearMe tab
                case 1: {
                    NearMeResults nearMeResults = (NearMeResults) getSupportFragmentManager().getFragments().get(1);
                    nearMeResults.setKeyword(query);
                    nearMeResults.refreshDataWithNewKeywordSearch();
                }
            }
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (searchRestaurantResultsLoadingLayout.getVisibility() == View.VISIBLE) {
            handleLoading(false);
        }
    }

    private void handleLoading(boolean isLoading) {
        if (isLoading) {
            handler.post(new Runnable() {

                @Override
                public void run() {
                    searchRestaurantResultsLoadingLayout.setVisibility(View.VISIBLE);
                }
            });
        } else handler.post(new Runnable() {

            @Override
            public void run() {
                searchRestaurantResultsLoadingLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onHandleLoading(boolean isLoading) {
        handleLoading(isLoading);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("SearchResultsActivity Destroyed");
    }


}