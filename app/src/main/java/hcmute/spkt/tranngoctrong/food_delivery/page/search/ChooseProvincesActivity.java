package hcmute.spkt.tranngoctrong.food_delivery.page.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.ProvinceAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.model.Province;

public class ChooseProvincesActivity extends AppCompatActivity {

    public static final String EXTRA_PROVINCE_SELECTED = "EXTRA_PROVINCE_SELECTED";

    RecyclerView provinceRecycleView;
    TextView doneTextview, backTextView;
    ProvinceAdapter provinceAdapter;
    Province currentProvinceSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_provinces);
        doneTextview = findViewById(R.id.doneTextView);
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

        doneTextview.setOnClickListener(new OnDoneTextClickListener());

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
            // send data back to search activity
            // đặt resultCode là Activity.RESULT_CANCELED thể hiện
            // đã thất bại khi người dùng click vào nút Back.
            // Khi này sẽ không trả về data.
            Intent resultIntent = new Intent();
            setResult(RESULT_CANCELED, resultIntent);
            finish();
        }
    }

    private List<Province> getListProvinces() {
        List<Province> provinces = new ArrayList<Province>();
        provinces.add(new Province("Quảng Ngãi", false));
        provinces.add(new Province("Quảng Nam", false));
        provinces.add(new Province("Đắk Lắk ", false));
        provinces.add(new Province("Đắk Nông", false));

        return provinces;

    }


}