package yahooweather.jsorgensen.com.yahooweatherapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import yahooweather.jsorgensen.com.yahooweatherapi.data.Channel;
import yahooweather.jsorgensen.com.yahooweatherapi.services.WeatherServiceCallback;
import yahooweather.jsorgensen.com.yahooweatherapi.services.YahooWeatherService;


public class MainActivity extends Activity implements WeatherServiceCallback {

    private ImageView condition_image;
    private TextView temperature, condition, location;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        condition_image = (ImageView)findViewById(R.id.condition_image);
        temperature = (TextView)findViewById(R.id.temperature);
        condition = (TextView)findViewById(R.id.condition);
        location = (TextView)findViewById(R.id.location);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Springville, UT");
    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        String image_url = channel.getImage().getUrl();
        String temperature_text = channel.getItem().getCondition().getTemperature() + channel.getUnits().getTemperature().toString();
        String condition_text = channel.getItem().getCondition().getDescription().toString();
        String location_text = service.getLocation().toString();

        //condition_image.setImageDrawable();
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
