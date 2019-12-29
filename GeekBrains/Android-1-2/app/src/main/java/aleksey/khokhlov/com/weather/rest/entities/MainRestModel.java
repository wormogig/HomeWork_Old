package aleksey.khokhlov.com.weather.rest.entities;

import com.google.gson.annotations.SerializedName;

public class MainRestModel {
    @SerializedName("temp") public float temp;
    @SerializedName("pressure") public int pressure;
    @SerializedName("humidity") public int humidity;
    @SerializedName("temp_min") public float tempMin;
    @SerializedName("tempMax") public float tempMax;
}
