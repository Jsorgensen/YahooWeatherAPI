package yahooweather.jsorgensen.com.yahooweatherapi;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yahooweather.jsorgensen.com.yahooweatherapi.R;

/**
 * Created by MECH on 10/31/2017.
 */

public class WeatherFragment extends Fragment {

    ViewGroup baseview;
    TextView temperature, condition, location;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
        baseview = inflater.inflate(R.id.weather_layout, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        temperature = (TextView)getView().findViewById(R.id.temperature);

    }
}
