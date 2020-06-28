package hcmute.spkt.tranngoctrong.food_delivery.views.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.ProvinceAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.model.Province;

public class ChooseProvincesActivity extends AppCompatActivity {

    public static final String EXTRA_PROVINCE_SELECTED = "EXTRA_PROVINCE_SELECTED";
    public static final String EXTRA_PROVINCE_SLUG_SELECTED = "EXTRA_PROVINCE_SLUG_SELECTED";

    // receive value from SearchRestaurantActivity
    public static final String SEARCH_PROVINCE_SLUG_EXTRA = "SEARCH_PROVINCE_SLUG_EXTRA";

    private RecyclerView provinceRecycleView;
    private TextView doneTextView, backTextView;
    private Province currentProvinceSelected;
    private ProvinceAdapter provinceAdapter;
    private SearchView provinceSearchView;
    private List<Province> provinces;
    private Handler handler;

    private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
            'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
            'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
            'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
            'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
            'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
            'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
            'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
            'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
            'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
            'ữ', 'Ự', 'ự',};

    private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
            'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
            'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
            'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
            'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u', 'U', 'u',};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_provinces);
        doneTextView = findViewById(R.id.doneTextView);
        backTextView = findViewById(R.id.backTextView);
        provinceSearchView = findViewById(R.id.search_view_province);
        provinceRecycleView = findViewById(R.id.provinceRecycleView);

        provinceRecycleView.setLayoutManager(new LinearLayoutManager(this));
        provinceRecycleView.setHasFixedSize(true);
        provinces = new ArrayList<Province>();

        provinces = getListProvinces();

        currentProvinceSelected = provinces.get(0);

        if (getIntent().getStringExtra(SEARCH_PROVINCE_SLUG_EXTRA) != null) {
            for (int i = 0; i < provinces.size(); i++) {
                if (provinces.get(i).getSlug()
                        .equalsIgnoreCase(getIntent().getStringExtra(SEARCH_PROVINCE_SLUG_EXTRA))) {
                    provinces.get(i).setSelected(true);
                }
            }
        }

        provinceAdapter = new ProvinceAdapter(this, provinces);
        provinceRecycleView.setAdapter(provinceAdapter);

        provinceAdapter.setOnItemClickListener(new ProvinceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Province province) {
                currentProvinceSelected = province;
                provinceAdapter.notifyDataSetChanged();
            }
        });

        provinceSearchView.setOnQueryTextListener(searchViewProvinceListener);

        doneTextView.setOnClickListener(new OnDoneTextClickListener());

        backTextView.setOnClickListener(new OnBackTextClickListener());
        handler = new Handler();
    }

    private class OnDoneTextClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // send data back to search activity
            String result = currentProvinceSelected.getName().toLowerCase();
            System.out.println(result);
            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_PROVINCE_SELECTED, currentProvinceSelected.getName());
            resultIntent.putExtra(EXTRA_PROVINCE_SLUG_SELECTED, currentProvinceSelected.getSlug());
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

    private SearchView.OnQueryTextListener searchViewProvinceListener = new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(final String newText) {
            System.out.println(newText);
            if (newText.isEmpty()) {
                provinceAdapter.setProvinces(getListProvinces());
            } else {
                provinces.clear();
                List<Province> fullProvinces;
                fullProvinces = getListProvinces();
                for (int i = 0; i < fullProvinces.size(); i++) {
                    if (removeAccent(fullProvinces.get(i).getName()).contains(newText)) {
                        provinces.add(getListProvinces().get(i));
                    }
                }
                provinceAdapter.setProvinces(provinces);
            }
            return true;
        }
    };

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


    public char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public String removeAccent(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }
}
