package hcmute.spkt.tranngoctrong.food_delivery.page.search;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;
import java.util.TreeMap;

import hcmute.spkt.tranngoctrong.food_delivery.R;

public class ChooseProvinceActivity extends AppCompatActivity {

    Map<String, String> provinces = new TreeMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.);
        putProvinces();

    }

    private void putProvinces() {
        provinces.put("quangngai", "TP.HCM");
        provinces.put("quangngai", "Hà Nội");
        provinces.put("quangngai", "Quảng Ngãi");

    }


}
