package hcmute.spkt.tranngoctrong.food_delivery.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.model.Province;

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.ProvinceHolder> {

    private List<Province> provinces;
    private LayoutInflater layoutInflater;
    private Context context;
    private int selectedItemIndex;
    private OnItemClickListener listener;

    public ProvinceAdapter(Context context, List<Province> listProvinces) {
        this.context = context;
        this.provinces = listProvinces;
        this.layoutInflater = LayoutInflater.from(context);
        this.selectedItemIndex = 0;
        for (Province province : listProvinces) {
            if (province.isSelected())
                this.selectedItemIndex = listProvinces.indexOf(province);
        }
    }

    @NonNull
    @Override
    public ProvinceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_province, parent, false);
        return new ProvinceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProvinceHolder holder, final int position) {
        Province currentProvince = provinces.get(position);
        holder.provinceNameTextView.setText(currentProvince.getName());
        if (this.selectedItemIndex == position) {
            holder.isSelectedProvinceButton.setImageResource(R.drawable.ic_check);
            holder.provinceNameTextView.setTextColor(Color.rgb(59, 88, 152));
        } else {
            holder.isSelectedProvinceButton.setImageResource(R.drawable.ic_check_empty);
            holder.provinceNameTextView.setTextColor(Color.rgb(91, 91, 92));

        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return provinces.size();
    }

    class ProvinceHolder extends RecyclerView.ViewHolder {
        AppCompatImageButton isSelectedProvinceButton;
        TextView provinceNameTextView;

        ProvinceHolder(View itemView) {
            super(itemView);
            isSelectedProvinceButton = itemView.findViewById(R.id.isSelectedProvinceButton);
            provinceNameTextView = (TextView) itemView.findViewById(R.id.provinceName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        selectedItemIndex = position;
                        listener.onItemClick(provinces.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Province province);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
