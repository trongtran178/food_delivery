package hcmute.spkt.tranngoctrong.food_delivery.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.model.Food;

public class MenuDetailsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> parentFood;
    private HashMap<String, List<Food>> childFood;


    public MenuDetailsAdapter(Context context, List<String> parentFood, HashMap<String, List<Food>> childFood) {
        this.context = context;
        this.parentFood = parentFood;
        this.childFood = childFood;
    }

    @Override
    public int getGroupCount() {
        return parentFood.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childFood.get(parentFood.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.parentFood.get(groupPosition);
    }

    @Override
    public Food getChild(int groupPosition, int childPosition) {
        return this.childFood.get(this.parentFood.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String groupTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.item_menu_group, null);
        }

        TextView itemMenuGroupTextView = (TextView) convertView.findViewById(R.id.item_menu_group_text_view);

        itemMenuGroupTextView.setTypeface(null, Typeface.BOLD);
        itemMenuGroupTextView.setText(groupTitle);

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childTitle = (String) getChild(groupPosition, childPosition).getName();
        float childPrice = (float) getChild(groupPosition, childPosition).getPrice();
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_menu_child, null);
        }
        TextView itemMenuChildTitleTextView = (TextView) convertView.findViewById(R.id.item_menu_child_title_text_view);
        TextView itemMenuChildPriceTextView = (TextView) convertView.findViewById(R.id.item_menu_child_price_text_view);
        itemMenuChildTitleTextView.setText(childTitle);
        String childPriceString = String.valueOf(childPrice);
        itemMenuChildPriceTextView.setText(childPriceString);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
