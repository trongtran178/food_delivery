package hcmute.spkt.tranngoctrong.food_delivery.model;

public class Restaurant {
    private String name;
    private String address;
    private float favoriteCount;

    public Restaurant() {
    }

    public Restaurant(String name, float favoriteCount) {
        this.name = name;
        this.favoriteCount = favoriteCount;
    }

    public float getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(float favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
