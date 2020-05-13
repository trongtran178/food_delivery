package hcmute.spkt.tranngoctrong.food_delivery.model;

public class User {
    private String phone;
    private String password;
    private boolean isAdmin;

    public User() {
    }

    public User(String phone, String password, boolean isAdmin) {
        this.phone = phone;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
