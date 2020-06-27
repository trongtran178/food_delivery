package hcmute.spkt.tranngoctrong.food_delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Province implements Serializable {
    private String name;
    private String slug;
    private boolean isSelected = false;


    public Province(String name, String slug, boolean isSelected) {
        this.name = name;
        this.slug = slug;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
