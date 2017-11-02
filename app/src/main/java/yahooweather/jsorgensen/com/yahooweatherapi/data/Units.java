package yahooweather.jsorgensen.com.yahooweatherapi.data;

import org.json.JSONObject;

/**
 * Created by MECH on 10/30/2017.
 */
public class Units  implements JSONPopulator{

    private String distance, pressure, speed, temperature;

    @Override
    public void populate(JSONObject data){

        distance = data.optString("distance");

        pressure = data.optString("pressure");

        speed = data.optString("speed");

        temperature = data.optString("temperature");
    }

    public String getTemperature() {
        return temperature;
    }

    public String getDistance() {
        return distance;
    }

    public String getPressure() {
        return pressure;
    }

    public String getSpeed() {
        return speed;
    }
}
