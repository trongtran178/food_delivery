package hcmute.spkt.tranngoctrong.food_delivery.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import androidx.annotation.NonNull;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.model.Wifi;
import hcmute.spkt.tranngoctrong.food_delivery.viewmodels.RestaurantDetailsViewModel;
import hcmute.spkt.tranngoctrong.food_delivery.viewmodels.SearchRestaurantResultsViewModel;
import hcmute.spkt.tranngoctrong.food_delivery.viewmodels.SearchRestaurantViewModel;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.RestaurantDetailsActivity;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.SearchRestaurantResultsActivity;

public class UpdateWifiPasswordDialog extends Dialog {

    private TextView wifiNameTextInputEditText, wifiPasswordTextInputEditText;
    private Button updateWifiPasswordDialogButton;
    private RestaurantDetailsViewModel restaurantDetailsViewModel;
    private Restaurant restaurant;
    private Context context;
    private SearchRestaurantViewModel searchRestaurantViewModel;
    private SearchRestaurantResultsViewModel searchRestaurantResultsViewModel;

    public UpdateWifiPasswordDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        setContentView(R.layout.dialog_add_wifi);
        restaurantDetailsViewModel = ViewModelProviders.of((AppCompatActivity) context).get(RestaurantDetailsViewModel.class);
    }

    @Override
    public void show() {
        super.show();
        wifiNameTextInputEditText = findViewById(R.id.restaurant_wifi_name_text_input_edit_text);
        wifiPasswordTextInputEditText = findViewById(R.id.restaurant_wifi_password_text_input_edit_text);
        updateWifiPasswordDialogButton = findViewById(R.id.update_wifi_password_button);

        wifiNameTextInputEditText.setText(restaurant.getWifi().getName());
        wifiPasswordTextInputEditText.setText(restaurant.getWifi().getPassword());

        updateWifiPasswordDialogButton.setOnClickListener(onUpdateWifiPasswordDialogButtonClick);
    }

    @Override
    public boolean isShowing() {
        return super.isShowing();
    }


    View.OnClickListener onUpdateWifiPasswordDialogButtonClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (wifiNameTextInputEditText.length() < 6) {
                Toast.makeText(context, "Minimum wifi name length  is 6 !!!", Toast.LENGTH_SHORT).show();
                return;
            } else if (wifiPasswordTextInputEditText.length() < 8) {
                Toast.makeText(context, "Minimum wifi password length  is 8 !!!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isUpdated = restaurantDetailsViewModel.updateWifi(restaurant.get_id(), wifiPasswordTextInputEditText.getText().toString());
            if (isUpdated) {
                Toast.makeText(context, "successfull !!!", Toast.LENGTH_SHORT).show();
                restaurant.setWifi(new Wifi(
                                wifiNameTextInputEditText.getText().toString(),
                                wifiPasswordTextInputEditText.getText().toString()
                        )

                );
                ((RestaurantDetailsActivity) context).handleUpdatedWifi(new Wifi
                        (wifiNameTextInputEditText.getText().toString(),
                                wifiPasswordTextInputEditText.getText().toString()));
                dismiss();

            } else
                Toast.makeText(context, "cac !!!", Toast.LENGTH_SHORT).show();
        }
    };


    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
