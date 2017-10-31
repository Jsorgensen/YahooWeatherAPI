package yahooweather.jsorgensen.com.yahooweatherapi.data;

import org.json.JSONObject;

/**
 * Created by MECH on 10/31/2017.
 */

public class Astronomy implements JSONPopulator {

    private String sunrise, sunset;

    @Override
    public void populate(JSONObject data) {
        sunrise = data.optString("sunrise");
        sunset = data.optString("sunset");
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }
}
