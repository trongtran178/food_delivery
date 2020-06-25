package hcmute.spkt.tranngoctrong.food_delivery.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.model.deserializer.DateDeserializer;
import hcmute.spkt.tranngoctrong.food_delivery.model.deserializer.WifiDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant implements Parcelable {
    private String _id;
    private String name;
    private String address;

    private String type;
    @JsonDeserialize(using = DateDeserializer.class)

    private Date timeOpen;
    @JsonDeserialize(using = DateDeserializer.class)
    private Date timeClose;

    private String phone;
    @JsonDeserialize(using = WifiDeserializer.class)
    private Wifi wifi;
    private String description;
    private String province;
    private String avatar;
    private String image;
    private List<String> imagesUrl;
    private List<String> foodQuery;

    private double latitude;
    private double longitude;
    private int __v;

    public Restaurant() {
    }

    public Restaurant(
            String _id,
            String name,
            String address,
            String type,
            Date timeOpen,
            Date timeClose,
            String phone,
            Wifi wifi,
            String description,
            String province,
            String avatar,
            String image,
            List<String> imagesUrl,
            List<String> foodQuery,
            double latitude,
            double longitude,
            int __v
    ) {
        this._id = _id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.timeOpen = timeOpen;
        this.timeClose = timeClose;
        this.phone = phone;
        this.wifi = wifi;
        this.description = description;
        this.province = province;
        this.avatar = avatar;
        this.image = image;
        this.imagesUrl = imagesUrl;
        this.foodQuery = foodQuery;
        this.latitude = latitude;
        this.longitude = longitude;
        this.__v = __v;
    }

    protected Restaurant(Parcel in) {
        _id = in.readString();
        name = in.readString();
        address = in.readString();
        type = in.readString();
        timeOpen = new Date(in.readLong());
        timeClose = new Date(in.readLong());
        phone = in.readString();
        description = in.readString();
        province = in.readString();
        avatar = in.readString();
        image = in.readString();
        imagesUrl = in.createStringArrayList();
        foodQuery = in.createStringArrayList();
        latitude = in.readDouble();
        longitude = in.readDouble();
        __v = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(type);
        dest.writeLong(timeOpen.getTime());
        dest.writeLong(timeClose.getTime());
        dest.writeString(phone);
        dest.writeString(description);
        dest.writeString(province);
        dest.writeString(avatar);
        dest.writeString(image);
        dest.writeStringList(imagesUrl);
        dest.writeStringList(foodQuery);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeInt(__v);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Wifi getWifi() {
        return wifi;
    }

    public void setWifi(Wifi wifi) {
        this.wifi = wifi;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public List<String> getFoodQuery() {
        return foodQuery;
    }

    public void setFoodQuery(List<String> foodQuery) {
        this.foodQuery = foodQuery;
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

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
