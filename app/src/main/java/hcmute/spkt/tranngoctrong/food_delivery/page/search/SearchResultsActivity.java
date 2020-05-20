package hcmute.spkt.tranngoctrong.food_delivery.page.search;

import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import hcmute.spkt.tranngoctrong.food_delivery.page.search.search_results_fragment.CommonResults;
import hcmute.spkt.tranngoctrong.food_delivery.page.search.search_results_fragment.MostRightResults;
import hcmute.spkt.tranngoctrong.food_delivery.page.search.search_results_fragment.NearMeResults;

public class SearchResultsActivity extends AppCompatActivity {

    private SectionsPagerAdapter sectionsPagerAdapter;

    private ViewPager viewPage;

    @Override
    protected void onCreate() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

    }

    public static class PlaceholderFragment extends Fragment {

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new MostRightResults();
                    break;
                case 1:
                    fragment = new NearMeResults();
                    break;
                case 2:
                    fragment = new CommonResults();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Most Right";
                case 1:
                    return "Near me";
                case 2:
                    return "Common";
            }
        }

    }


}
