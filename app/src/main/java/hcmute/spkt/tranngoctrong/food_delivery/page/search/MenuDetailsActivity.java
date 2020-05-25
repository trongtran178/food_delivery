package hcmute.spkt.tranngoctrong.food_delivery.page.search;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.Metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.MenuDetailsAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.model.Food;

public class MenuDetailsActivity extends AppCompatActivity {

    ExpandableListView menuDetailsExpandableListView;
    ExpandableListAdapter menuDetailsExpandableListAdapter;
    List<String> itemsMenuGroup;
    HashMap<String, List<Food>> itemsMenuChild;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);

        menuDetailsExpandableListView = findViewById(R.id.menu_details_expandable_list_view);

        itemsMenuChild = getData();
        itemsMenuGroup = new ArrayList<String>(itemsMenuChild.keySet());

        menuDetailsExpandableListAdapter = new MenuDetailsAdapter(this, itemsMenuGroup, itemsMenuChild);
        menuDetailsExpandableListView.setAdapter(menuDetailsExpandableListAdapter);

        menuDetailsExpandableListView.expandGroup(0);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        menuDetailsExpandableListView.setIndicatorBounds(width - GetDipsFromPixel(50.0f), width - GetDipsFromPixel(10.0f));

    }

    private int GetDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    private HashMap<String, List<Food>> getData() {
        HashMap<String, List<Food>> data = new HashMap<String, List<Food>>();

        List<Food> childBeef = new ArrayList<Food>();
        childBeef.add(new Food(1, "Bo nhung giam nho", 50000));
        childBeef.add(new Food(2, "Bo nhung giam vua", 70000));
        childBeef.add(new Food(3, "Bo nhung giam lon", 90000));

        List<Food> childBeanVermicelli = new ArrayList<Food>();
        childBeanVermicelli.add(new Food(1, "Bun dau nho", 50000));
        childBeanVermicelli.add(new Food(2, "Bun dau vua", 70000));
        childBeanVermicelli.add(new Food(3, "Bun dau lon", 90000));

        List<Food> childExtraDishes = new ArrayList<Food>();
        childExtraDishes.add(new Food(1, "Bo nhung giam nho", 50000));
        childExtraDishes.add(new Food(2, "Bo nhung giam vua", 70000));
        childExtraDishes.add(new Food(3, "Bo nhung giam lon", 90000));

        data.put("Beef dipped in vinegar", childBeef);
        data.put("Bean vermicelli", childBeanVermicelli);
        data.put("Extra dishes", childExtraDishes);

        return data;

    }
}
