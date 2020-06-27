package hcmute.spkt.tranngoctrong.food_delivery.views.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.ProvinceAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.model.Province;

public class ChooseProvincesActivity extends AppCompatActivity {

    public static final String EXTRA_PROVINCE_SELECTED = "EXTRA_PROVINCE_SELECTED";
    public static final String EXTRA_PROVINCE_SLUG_SELECTED = "EXTRA_PROVINCE_SLUG_SELECTED";

    RecyclerView provinceRecycleView;
    TextView doneTextView, backTextView;
    ProvinceAdapter provinceAdapter;
    Province currentProvinceSelected;

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern SEPARATORS = Pattern.compile("[\\s\\p{Punct}&&[^-]]");

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
            String result = currentProvinceSelected.getName().toLowerCase();
            System.out.println(result);
            System.out.println(toSlug(result));
            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_PROVINCE_SELECTED, currentProvinceSelected.getName());
            resultIntent.putExtra(EXTRA_PROVINCE_SLUG_SELECTED, currentProvinceSelected.getSlug());
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }

    private String toSlug(String input) {

        String noseparators = SEPARATORS.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(noseparators, Normalizer.Form.NFKC);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.CANADA).replaceAll("-{2,}", "-").replaceAll("^-|-$", "");

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
        provinces.add(new Province("An Giang", "an-giang", false));
        provinces.add(new Province("Bà Rịa - Vũng Tàu", "ba-ria-vung-tau", false));
        provinces.add(new Province("Bắc Giang", "bac-giang", false));
        provinces.add(new Province("Bắc Kạn", "bac-kan", false));
        provinces.add(new Province("Bạc Liêu", "bac-lieu", false));
        provinces.add(new Province("Bắc Ninh", "bac -ninh", false));
        provinces.add(new Province("Bến Tre", "ben-tre", false));
        provinces.add(new Province("Bình Định", "binh-dinh", false));
        provinces.add(new Province("Bình Dương", "binh-duong", false));
        provinces.add(new Province("Bình Phước", "binh-phuoc", false));
        provinces.add(new Province("Bình Thuận", "binh-thuan", false));
        provinces.add(new Province("Cà Mau", "ca-mau", false));
        provinces.add(new Province("Cao Bằng", "cao-bang", false));
        provinces.add(new Province("Đắk Lắk", "dak-lak", false));
        provinces.add(new Province("Đắk Nông", "dak-nong", false));
        provinces.add(new Province("Điện Biên", "dien-bien", false));
        provinces.add(new Province("Đồng Nai", "dong-nai", false));
        provinces.add(new Province("Đồng Tháp", "dong-thap", false));
        provinces.add(new Province("Gia Lai", "gia-lai", false));
        provinces.add(new Province("Hà Giang", "ha-giang", false));
        provinces.add(new Province("Hà Nam", "ha-nam", false));
        provinces.add(new Province("Hà Tĩnh", "ha-tinh", false));
        provinces.add(new Province("Hải Dương", "hai-duong", false));
        provinces.add(new Province("Hậu Giang", "hau-giang", false));
        provinces.add(new Province("Hòa Bình", "hoa-binh", false));
        provinces.add(new Province("Hưng Yên", "hung-yen", false));
        provinces.add(new Province("Khánh Hòa", "khanh-hoa", false));
        provinces.add(new Province("Kiên Giang", "kien-giang", false));
        provinces.add(new Province("Kon Tum", "kon-tum", false));
        provinces.add(new Province("Lai Châu", "lai-chau", false));
        provinces.add(new Province("Lâm Đồng", "lam-dong", false));
        provinces.add(new Province("Lạng Sơn", "lang-son", false));
        provinces.add(new Province("Lào Cai", "lao-cai", false));
        provinces.add(new Province("Long An", "long-an", false));
        provinces.add(new Province("Nam Định", "nam-dinh", false));
        provinces.add(new Province("Nghệ An", "nghe-an", false));
        provinces.add(new Province("Ninh Bình", "ninh-binh", false));
        provinces.add(new Province("Ninh Thuận", "ninh-thuan", false));
        provinces.add(new Province("Phú Thọ", "phu-tho", false));
        provinces.add(new Province("Quảng Bình", "quang-binh", false));
        provinces.add(new Province("Quảng Nam", "quang-nam", false));
        provinces.add(new Province("Quảng Ngãi", "quang-ngai", false));
        provinces.add(new Province("Quảng Ninh", "quang-ninh", false));
        provinces.add(new Province("Quảng Trị", "quang-tri", false));
        provinces.add(new Province("Sóc Trăng", "soc-trang", false));
        provinces.add(new Province("Sơn La", "son-la", false));
        provinces.add(new Province("Tây Ninh", "tay-ninh", false));
        provinces.add(new Province("Thái Bình", "thai-binh", false));
        provinces.add(new Province("Thái Nguyên", "thai-nguyen", false));
        provinces.add(new Province("Thanh Hóa", "thanh-hoa", false));
        provinces.add(new Province("Thừa Thiên Huế", "thua-thien-hue", false));
        provinces.add(new Province("Tiền Giang", "tien-giang", false));
        provinces.add(new Province("Trà Vinh", "tra-vinh", false));
        provinces.add(new Province("Đà Nẵng", "da-nang", false));
        provinces.add(new Province("Hải Phòng", "hai-phong", false));
        provinces.add(new Province("Hà Nội", "ha-noi", false));
        provinces.add(new Province("TP HCM", "ho-chi-minh", false));

        return provinces;

    }


}
