package hcmute.spkt.tranngoctrong.food_delivery.model;

import java.io.Serializable;
import java.util.List;

public class FoodCategory implements Serializable {
    private String _id;
    private String name;
    private List<Food> foods;
    private int __v;

    public FoodCategory() {
    }

    public FoodCategory(String _id, String name, List<Food> foods, int __v) {
        this._id = _id;
        this.name = name;
        this.foods = foods;
        this.__v = __v;
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

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
