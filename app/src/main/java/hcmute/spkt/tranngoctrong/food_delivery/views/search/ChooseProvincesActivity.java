package hcmute.spkt.tranngoctrong.food_delivery.views.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.ProvinceAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.model.Province;

public class ChooseProvincesActivity extends AppCompatActivity {

    public static final String EXTRA_PROVINCE_SELECTED = "EXTRA_PROVINCE_SELECTED";

    RecyclerView provinceRecycleView;
    TextView doneTextView, backTextView;
    ProvinceAdapter provinceAdapter;
    Province currentProvinceSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_provinces);
        doneTextView = findViewById(R.id.doneTextView);
        backTextView = findViewById(R.id.backTextView);
        provinceRecycleView = findViewById(R.id.provinceRecycleView);
        provinceRecycleView.setLayoutManager(new LinearLayoutManager(this));
        provinceRecycleView.setHasFixedSize(true);
        final List<Province> provinces = getListProvinces();
        currentProvinceSelected = provinces.get(0);
        System.out.println(provinces.size());
        provinceAdapter = new ProvinceAdapter(this, provinces);
        provinceRecycleView.setAdapter(provinceAdapter);

        provinceAdapter.setOnItemClickListener(new ProvinceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Province province) {
                currentProvinceSelected = province;
                System.out.println(province.getName());
                provinceAdapter.notifyDataSetChanged();
            }
        });

        doneTextView.setOnClickListener(new OnDoneTextClickListener());

        backTextView.setOnClickListener(new OnBackTextClickListener());

    }

    private class OnDoneTextClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // send data back to search activity
            String result = currentProvinceSelected.getName();
            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_PROVINCE_SELECTED, result);

            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }

    private class OnBackTextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent resultIntent = new Intent();
            setResult(RESULT_CANCELED, resultIntent);
            finish();
        }
    }

    private List<Province> getListProvinces() {
        List<Province> provinces = new ArrayList<Province>();
        provinces.add(new Province("Ho Chi Minh", false));
        provinces.add(new Province("Quang Tri", false));
        provinces.add(new Province("Thua Thien - Hue", false));
        provinces.add(new Province("Da Nang", false));

        return provinces;

    }


}
