package aleksey.khokhlov.com.weather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import aleksey.khokhlov.com.weather.rest.OpenWeatherRepo;
import aleksey.khokhlov.com.weather.rest.entities.WeatherRequestRestModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentWeather extends Fragment {

    public static final String TAG = FragmentWeather.class.getSimpleName();

    public static final String EXTRA_CITY = "city";
    public static final String EXTRA_WIND = "wind";
    public static final String EXTRA_HUMIDITY = "humidity";
    public static final String EXTRA_PRESSURE = "pressure";

    private static final String API_KEY = "dd4bcc5853bf6646988325f3dde49c71";

    private TextView textTemperature;
    private TextView textHumidity;
    private TextView textPressure;
    private TextView textWind;
    private TextView textCity;
    private ImageView imageView;

    private LinearLayout llHumidity;
    private LinearLayout llPressure;
    private LinearLayout llWind;

    private String city = "";

    private final Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViews(view);
        final SharedPreferences defaultPrefs = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext());
        readFromPreference(defaultPrefs);
        requestRetrofit(city);
    }

    private void setViews(@NonNull View view) {
        textTemperature = view.findViewById(R.id.text_temperature);
        textHumidity = view.findViewById(R.id.text_humidity);
        textPressure = view.findViewById(R.id.text_pressure);
        textWind = view.findViewById(R.id.text_wind);
        textCity = view.findViewById(R.id.text_city);
        imageView = view.findViewById(R.id.image_view);
        llHumidity = view.findViewById(R.id.ll_humidity);
        llPressure = view.findViewById(R.id.ll_pressure);
        llWind = view.findViewById(R.id.ll_wind);
    }


    private void readFromPreference(SharedPreferences preferences) {
        textCity.setText(preferences.getString(EXTRA_CITY, ""));
        if (preferences.getBoolean(EXTRA_HUMIDITY, false)) {
            llVisible(llHumidity);
        }
        if (preferences.getBoolean(EXTRA_PRESSURE, false)) {
            llVisible(llPressure);
        }
        if (preferences.getBoolean(EXTRA_WIND, false)) {
            llVisible(llWind);
        }
        city = preferences.getString(EXTRA_CITY, "Moscow");
    }

    private void llVisible(LinearLayout linearLayout) {
        linearLayout.setVisibility(View.VISIBLE);
    }

    private void requestRetrofit (String city) {
        OpenWeatherRepo.getSingleton().getAPI().loadWeather(city +", ru", API_KEY, "metric")
        .enqueue(new Callback<WeatherRequestRestModel>() {
            @Override
            public void onResponse(@NonNull Call<WeatherRequestRestModel> call,
                                   @NonNull Response<WeatherRequestRestModel> response) {
                if (response.body() != null && response.isSuccessful()) {
                    WeatherRequestRestModel model = response.body();
                    renderWeather(model);
                }
            }

            @Override
            public void onFailure(Call<WeatherRequestRestModel> call, Throwable t) {

            }
        });
    }

    private void renderWeather(WeatherRequestRestModel model) {
        setPlace(model);
        setTemperature(model);
        setPressure(model);
        setHumidity(model);
        setWind(model);
        setImage(model);
    }

    private void setPlace(WeatherRequestRestModel model){
        String text = model.name;
        textCity.setText(text);
    }

    private void setTemperature(WeatherRequestRestModel model) {
        String text = " " + model.main.temp + "º";
        textTemperature.setText(text);
    }
    private void setPressure(WeatherRequestRestModel model) {
        String text = " " + model.main.pressure + " гПа";
        textPressure.setText(text);
    }
    private void setHumidity(WeatherRequestRestModel model) {
        String text = " " + model.main.humidity + "%";
        textHumidity.setText(text);
    }
    private void setWind(WeatherRequestRestModel model) {
        String text = " " + model.wind.speed + " м/с";
        textWind.setText(text);
    }

    private void setImage (WeatherRequestRestModel model) {
        String baseURL = "http://openweathermap.org/img/w/%s.png";
        Picasso.get().load(String.format(baseURL, model.weather[0].icon))
                .resize(400,400)
                .into(imageView);

    }
}
