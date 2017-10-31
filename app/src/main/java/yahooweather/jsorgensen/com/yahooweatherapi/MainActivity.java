package yahooweather.jsorgensen.com.yahooweatherapi;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import yahooweather.jsorgensen.com.yahooweatherapi.data.Channel;
import yahooweather.jsorgensen.com.yahooweatherapi.services.WeatherServiceCallback;
import yahooweather.jsorgensen.com.yahooweatherapi.services.YahooWeatherService;


public class MainActivity extends Activity implements WeatherServiceCallback {

    private YahooWeatherService service;
    private ProgressDialog dialog;

    private WeatherFragment weatherFragment;
    private ForecastFragment forecastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Springville, UT");

        weatherFragment = new WeatherFragment();
        forecastFragment = new ForecastFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(weatherFragment);
        fragmentTransaction.show(forecastFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        String temperature_text = channel.getItem().getCondition().getTemperature() + channel.getUnits().getTemperature().toString();
        String condition_text = channel.getItem().getCondition().getDescription().toString();
        String location_text = service.getLocation().toString();

        temperature.setText(temperature_text);
        condition.setText(condition_text);
        location.setText(location_text);
    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
