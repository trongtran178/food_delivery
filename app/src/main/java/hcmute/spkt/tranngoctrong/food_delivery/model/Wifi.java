package hcmute.spkt.tranngoctrong.food_delivery.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wifi implements Parcelable {

    private String name;
    private String password;

    public Wifi() {
    }

    public Wifi(String name, String password) {
        this.name = name;
        this.password = password;
    }

    protected Wifi(Parcel in) {
        name = in.readString();
        password = in.readString();
    }

    public static final Creator<Wifi> CREATOR = new Creator<Wifi>() {
        @Override
        public Wifi createFromParcel(Parcel in) {
            return new Wifi(in);
        }

        @Override
        public Wifi[] newArray(int size) {
            return new Wifi[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Wifi fromJson(String wifiJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(wifiJson, Wifi.class);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(password);
    }

    @Override
    public String toString() {
        return "Wifi{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
