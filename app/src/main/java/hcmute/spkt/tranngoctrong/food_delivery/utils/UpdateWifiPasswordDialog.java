package hcmute.spkt.tranngoctrong.food_delivery.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import androidx.annotation.NonNull;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.viewmodels.RestaurantDetailsViewModel;

public class UpdateWifiPasswordDialog extends Dialog {

    private Button updateWifiPasswordDialogButton;
    private RestaurantDetailsViewModel restaurantDetailsViewModel;


    @Override
    public boolean isShowing() {
        return super.isShowing();
    }

    @Override
    public void show() {
        super.show();
        updateWifiPasswordDialogButton = findViewById(R.id.update_wifi_password_button);
        updateWifiPasswordDialogButton.setOnClickListener(onUpdateWifiPasswordDialogButtonClick);
    }

    public UpdateWifiPasswordDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_add_wifi);
        restaurantDetailsViewModel = ViewModelProviders.of((AppCompatActivity) context).get(RestaurantDetailsViewModel.class);

    }

    View.OnClickListener onUpdateWifiPasswordDialogButtonClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            restaurantDetailsViewModel.updateWifi("12345");

        }
    };

}
