package hcmute.spkt.tranngoctrong.food_delivery.views.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.viewmodels.SearchRestaurantResultsViewModel;
import hcmute.spkt.tranngoctrong.food_delivery.viewmodels.SearchRestaurantViewModel;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.search_results_fragment.CommonResults;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.search_results_fragment.Filters;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.search_results_fragment.MostRightResults;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.search_results_fragment.NearMeResults;

public class SearchRestaurantResultsActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SearchView searchRestaurantResultsView;
    private ImageButton search_results_back_button;
    private String searchQuery;
    private static final String SEARCH_QUERY_EXTRA = "SEARCH_QUERY_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurant_results);

        searchRestaurantResultsView = findViewById(R.id.search_restaurant_results_view);
        tabLayout = findViewById(R.id.search_results_tab);
        viewPager = findViewById(R.id.search_results_container);
        search_results_back_button = findViewById(R.id.search_results_back_button);

        Intent intent = getIntent();
        searchQuery = intent.getStringExtra(SEARCH_QUERY_EXTRA);
        searchRestaurantResultsView.setQuery(searchQuery, true);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        searchRestaurantResultsView.setOnQueryTextListener(searchViewQueryTextListener);
        search_results_back_button.setOnClickListener(searchResultsBackButtonClickListener);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager(), SectionsPageAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new MostRightResults(searchQuery), "Most right");
        adapter.addFragment(new NearMeResults(searchQuery), "Near me");
        adapter.addFragment(new CommonResults(), "Common");
        adapter.addFragment(new Filters(), "Filter");
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
            System.out.println(query);
            switch (tabLayout.getSelectedTabPosition()) {
                case 0: {
                    MostRightResults mostRightResults = (MostRightResults) getSupportFragmentManager().getFragments().get(0);
                    mostRightResults.setKeyword(query);
                    mostRightResults.refreshDataWithNewKeywordSearch();
                }
                case 1: {
                    NearMeResults nearMeResults = (NearMeResults) getSupportFragmentManager().getFragments().get(1);
                    nearMeResults.setKeyword(query);
                    nearMeResults.refreshDataWithNewKeywordSearch();
                }
            }
            System.out.println(tabLayout.getSelectedTabPosition());
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("SearchResultsActivity Destroyed");
    }

}