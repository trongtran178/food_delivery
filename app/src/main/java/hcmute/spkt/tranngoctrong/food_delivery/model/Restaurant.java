package hcmute.spkt.tranngoctrong.food_delivery.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


import java.util.Date;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.model.deserializer.DateDeserializer;
import hcmute.spkt.tranngoctrong.food_delivery.model.deserializer.FoodMenuDeserializer;

public class Restaurant implements Parcelable {
    private int id;
    private String name;
    private String address;
    private String phone;

    @JsonDeserialize(using = DateDeserializer.class)

    private Date timeOpen;
    @JsonDeserialize(using = DateDeserializer.class)
    private Date timeClose;
    private String description;
    private String province;
    private String avatarUrl;
    private List<String> imagesUrl;
    private String wifi;
    private double latitude;
    private double longitude;

    public Restaurant() { }

    public Restaurant(int id, String name, String address, String phone, Date timeOpen, Date timeClose, String description, String province, String avatarUrl, List<String> imagesUrl, String wifi, double latitude, double longitude) {
        this.id = id;
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
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected Restaurant(Parcel in) {
        id = in.readInt();
        name = in.readString();
        address = in.readString();
        phone = in.readString();
        description = in.readString();
        province = in.readString();
        avatarUrl = in.readString();
        imagesUrl = in.createStringArrayList();
        wifi = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        timeOpen = new Date(in.readLong());
        timeClose = new Date(in.readLong());
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(phone);
        dest.writeString(description);
        dest.writeString(province);
        dest.writeString(avatarUrl);
        dest.writeStringList(imagesUrl);
        dest.writeString(wifi);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeLong(timeOpen.getTime());
        dest.writeLong(timeClose.getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public static Creator<Restaurant> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", timeOpen=" + timeOpen +
                ", timeClose=" + timeClose +
                ", description='" + description + '\'' +
                ", province='" + province + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", imagesUrl=" + imagesUrl +
                ", wifi='" + wifi + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
