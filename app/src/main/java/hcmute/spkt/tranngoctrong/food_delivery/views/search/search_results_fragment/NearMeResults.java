package hcmute.spkt.tranngoctrong.food_delivery.views.search.search_results_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hcmute.spkt.tranngoctrong.food_delivery.R;

public class NearMeResults extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_near_me, container, false);
    }
}
