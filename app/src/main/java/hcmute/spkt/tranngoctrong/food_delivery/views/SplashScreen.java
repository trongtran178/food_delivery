package hcmute.spkt.tranngoctrong.food_delivery.views;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.SearchRestaurantActivity;

public class SplashScreen extends Activity {

    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    private ImageView splashIcon1, splashIcon2, splashIcon3, splashIcon4;
    private AssetManager assetManager;
    private InputStream inputStream;
    private Bitmap bitmap1, bitmap2, bitmap3, bitmap4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        assetManager = getAssets();
        splashIcon1 = findViewById(R.id.splash_icon_1);
        splashIcon2 = findViewById(R.id.splash_icon_2);
        splashIcon3 = findViewById(R.id.splash_icon_3);
        splashIcon4 = findViewById(R.id.splash_icon_4);

        try {
            inputStream = assetManager.open("icons/cake.png");
            bitmap1 = BitmapFactory.decodeStream(inputStream);
            splashIcon1.setImageBitmap(bitmap1);

            inputStream = assetManager.open("icons/soup.png");
            bitmap2 = BitmapFactory.decodeStream(inputStream);
            splashIcon2.setImageBitmap(bitmap2);

            inputStream = assetManager.open("icons/fish-food.png");
            bitmap3 = BitmapFactory.decodeStream(inputStream);
            splashIcon3.setImageBitmap(bitmap3);

            inputStream = assetManager.open("icons/the-toast.png");
            bitmap4 = BitmapFactory.decodeStream(inputStream);
            splashIcon4.setImageBitmap(bitmap4);

        } catch (IOException e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreen.this, SearchRestaurantActivity.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
