package hcmute.spkt.tranngoctrong.food_delivery.page.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.page.search.search_results_fragment.CommonResults;
import hcmute.spkt.tranngoctrong.food_delivery.page.search.search_results_fragment.Filters;
import hcmute.spkt.tranngoctrong.food_delivery.page.search.search_results_fragment.MostRightResults;
import hcmute.spkt.tranngoctrong.food_delivery.page.search.search_results_fragment.NearMeResults;

public class SearchResultsActivity extends AppCompatActivity {

    private SectionsPageAdapter sectionsPagerAdapter;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SearchView searchRestaurantResultsView;
    private ImageButton search_results_back_button;
    private static final String SEARCH_QUERY_EXTRA = "SEARCH_QUERY_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        searchRestaurantResultsView = (SearchView) findViewById(R.id.search_restaurant_results_view);

        Intent intent = getIntent();
        String searchQuery = intent.getStringExtra(SEARCH_QUERY_EXTRA);
        searchRestaurantResultsView.setQuery(searchQuery, true);
        sectionsPagerAdapter = new SectionsPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabLayout = (TabLayout) findViewById(R.id.search_results_tab);
        viewPager = (ViewPager) findViewById(R.id.search_results_container);
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
        search_results_back_button = findViewById(R.id.search_results_back_button);
        search_results_back_button.setOnClickListener(searchResultsBackButtonClickListener);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager(), SectionsPageAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new MostRightResults(), "Most right");
        adapter.addFragment(new NearMeResults(), "Near me");
        adapter.addFragment(new CommonResults(), "Common");
        adapter.addFragment(new Filters(), "Filter");
        viewPager.setAdapter(adapter);
    }

    private View.OnClickListener searchResultsBackButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SearchResultsActivity.super.finish();
        }
    };


}