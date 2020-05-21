package hcmute.spkt.tranngoctrong.food_delivery.page.search;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.page.search.search_results_fragment.CommonResults;
import hcmute.spkt.tranngoctrong.food_delivery.page.search.search_results_fragment.MostRightResults;
import hcmute.spkt.tranngoctrong.food_delivery.page.search.search_results_fragment.NearMeResults;

public class SearchResultsActivity extends AppCompatActivity {

    private SectionsPageAdapter sectionsPagerAdapter;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        sectionsPagerAdapter = new SectionsPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabLayout = (TabLayout) findViewById(R.id.search_results_tab);
        viewPager = (ViewPager) findViewById(R.id.search_results_container);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager(), SectionsPageAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new MostRightResults(), "Most right");
        adapter.addFragment(new NearMeResults(), "Near me");
        adapter.addFragment(new CommonResults(), "Common");

        viewPager.setAdapter(adapter);
    }


}