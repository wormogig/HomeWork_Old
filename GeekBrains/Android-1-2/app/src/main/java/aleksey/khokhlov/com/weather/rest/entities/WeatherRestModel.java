package aleksey.khokhlov.com.weather.rest.entities;

import com.google.gson.annotations.SerializedName;

public class WeatherRestModel {
    @SerializedName("id") public int id;
    @SerializedName("main") public String name;
    @SerializedName("description") public String description;
    @SerializedName("icon") public String icon;
}
