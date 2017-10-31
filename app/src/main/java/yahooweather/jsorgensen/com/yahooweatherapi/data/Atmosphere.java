package yahooweather.jsorgensen.com.yahooweatherapi.data;

import org.json.JSONObject;

/**
 * Created by MECH on 10/31/2017.
 */

public class Atmosphere implements JSONPopulator {

    private double humidity, pressure, rising, visibility;

    @Override
    public void populate(JSONObject data) {
        humidity = data.optDouble("humidity");
        pressure = data.optDouble("pressure");
        rising = data.optDouble("rising");
        visibility = data.optDouble("visibility");
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public double getRising() {
        return rising;
    }

    public double getVisibility() {
        return visibility;
    }
}
