package hcmute.spkt.tranngoctrong.food_delivery.model;

import java.io.Serializable;

public class Province implements Serializable {
    private String name;
    private boolean isSelected = false;

    public Province(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
