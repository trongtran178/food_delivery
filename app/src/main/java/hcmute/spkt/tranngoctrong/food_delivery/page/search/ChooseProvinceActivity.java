package hcmute.spkt.tranngoctrong.food_delivery.page.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;
import java.util.TreeMap;

import hcmute.spkt.tranngoctrong.food_delivery.R;

public class ChooseProvinceActivity extends AppCompatActivity {

    public static final String EXTRA_PROVINCE = "EXTRA_PROVINCE";

    Map<String, String> provinces = new TreeMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provinces);

//        putProvinces();
//
//        final Intent data = new Intent();
//
//        data.putExtra(EXTRA_PROVINCE, "Quang Ngai");
//
//        setResult(Activity.RESULT_OK, data);
//        finish();


    }

    @Override
    public void onBackPressed() {

        // đặt resultCode là Activity.RESULT_CANCELED thể hiện
        // đã thất bại khi người dùng click vào nút Back.
        // Khi này sẽ không trả về data.
        setResult(AppCompatActivity.RESULT_CANCELED);
        super.onBackPressed();
    }

    private void putProvinces() {
        provinces.put("quangngai", "TP.HCM");
        provinces.put("quangngai", "Hà Nội");
        provinces.put("quangngai", "Quảng Ngãi");

    }


}
