package aleksey.khokhlov.com.weather.DataLoaders;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class OpenWeatherMapLoader {
    private static final String OPEN_WEATHER_API_KEY = "dd4bcc5853bf6646988325f3dde49c71";
    private static final String OPEN_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
    private static final String KEY = "x-api-key";
    private static final String RESPONSE = "cod";
    private static final int ALL_GOOD = 200;

    public static JSONObject getJSONData (String city) {
        try {
            URL url = new URL(String.format(OPEN_WEATHER_API_URL, city));
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.addRequestProperty(KEY, OPEN_WEATHER_API_KEY);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder strBuilder = new StringBuilder(1024);
            String temp;

            while ((temp = reader.readLine()) != null) {
                strBuilder.append(temp).append("\n");
            }

            reader.close();

            JSONObject jsonObject = new JSONObject(strBuilder.toString());
            if (jsonObject.getInt(RESPONSE) == ALL_GOOD) {
                return jsonObject;
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
