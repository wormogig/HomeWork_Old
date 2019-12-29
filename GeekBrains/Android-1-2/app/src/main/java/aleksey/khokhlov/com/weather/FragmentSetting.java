package aleksey.khokhlov.com.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class FragmentSetting extends Fragment {

    public static final String TAG = FragmentSetting.class.getSimpleName();

    private CheckBox chbWind;
    private CheckBox chbHumidity;
    private CheckBox chbPressure;
    private EditText editCity;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViews(view);
        final SharedPreferences defaultPrefs = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext());
        readFromPreference(defaultPrefs);
    }

    private void setViews(@NonNull View view) {
        chbWind = view.findViewById(R.id.chbWind);
        chbHumidity = view.findViewById(R.id.chbHumidity);
        chbPressure = view.findViewById(R.id.chbPressure);
        editCity = view.findViewById(R.id.edit_city);
        setListenerDisableKeyboard(view);

    }

    private void setListenerDisableKeyboard(@NonNull View view) {
        editCity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    Context context = getContext();
                    InputMethodManager in = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(editCity.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                InputMethodManager in = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(editCity.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });
    }

    private void saveToPreference(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(FragmentWeather.EXTRA_WIND, chbWind.isChecked());
        editor.putBoolean(FragmentWeather.EXTRA_HUMIDITY, chbHumidity.isChecked());
        editor.putBoolean(FragmentWeather.EXTRA_PRESSURE, chbPressure.isChecked());
        editor.putString(FragmentWeather.EXTRA_CITY, editCity.getText().toString());
        editor.apply();
    }

    private void readFromPreference(SharedPreferences preferences) {
        chbWind.setChecked(preferences.getBoolean(FragmentWeather.EXTRA_WIND, false));
        chbHumidity.setChecked(preferences.getBoolean(FragmentWeather.EXTRA_HUMIDITY, false));
        chbPressure.setChecked(preferences.getBoolean(FragmentWeather.EXTRA_PRESSURE, false));
        editCity.setText(preferences.getString(FragmentWeather.EXTRA_CITY, "Moscow"));
    }


    @Override
    public void onStop() {
        super.onStop();
        final SharedPreferences defaultPrefs = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext());
        saveToPreference(defaultPrefs);
    }

}
