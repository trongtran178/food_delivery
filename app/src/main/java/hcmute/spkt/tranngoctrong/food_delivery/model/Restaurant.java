package hcmute.spkt.tranngoctrong.food_delivery.model;

public class Restaurant {
    private String name;
    private String address;
    private float rating; // 0 < rating <= 5, minimum distance is .5
    private float distanceToUser;
    
    public Restaurant() {
    }

    public Restaurant(String name, String address, float rating, float distanceToUser) {
        this.name = name;
        this.address = address;
        this.distanceToUser = distanceToUser;
        this.rating = rating;
    }

    public Restaurant(String name, float rating) {
        this.name = name;
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
