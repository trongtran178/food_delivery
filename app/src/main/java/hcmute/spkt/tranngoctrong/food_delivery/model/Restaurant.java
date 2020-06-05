package hcmute.spkt.tranngoctrong.food_delivery.model;


import java.util.Date;
import java.util.List;

public class Restaurant {

    private String name;
    private String address;
    private String phone;
    private Date timeOpen;
    private Date timeClose;
    private String description;
    private String province;
    private String avatarUrl;
    private List<String> imagesUrl;
    private String wifi;

    public Restaurant() {
        super();
    }

    public Restaurant( //
                       String name, //
                       String address, //
                       String phone, //
                       Date timeOpen, //
                       Date timeClose, //
                       String description, //
                       String province, //
                       String avatarUrl, //
                       List<String> imagesUrl, //
                       String wifi //
    ) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.timeOpen = timeOpen;
        this.timeClose = timeClose;
        this.description = description;
        this.province = province;
        this.avatarUrl = avatarUrl;
        this.imagesUrl = imagesUrl;
        this.wifi = wifi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(Date timeOpen) {
        this.timeOpen = timeOpen;
    }

    public Date getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(Date timeClose) {
        this.timeClose = timeClose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", timeOpen=" + timeOpen +
                ", timeClose=" + timeClose +
                ", description='" + description + '\'' +
                ", province='" + province + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", imagesUrl=" + imagesUrl +
                ", wifi='" + wifi + '\'' +
                '}';
    }
}
