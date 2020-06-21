package hcmute.spkt.tranngoctrong.food_delivery.model;

import java.io.Serializable;

public class Food {
    private String _id;
    private String name;
    private String slug;
    private String image;
    private double price;

    public Food() {
    }

    public Food(String _id, String name, String slug, String image, double price) {
        this._id = _id;
        this.name = name;
        this.slug = slug;
        this.image = image;
        this.price = price;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
