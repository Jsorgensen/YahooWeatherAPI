package yahooweather.jsorgensen.com.yahooweatherapi.data;

import org.json.JSONObject;

/**
 * Created by MECH on 10/31/2017.
 */

public class LocationWeather implements JSONPopulator {

    private String city, country, region;

    @Override
    public void populate(JSONObject data) {
        city = data.optString("city");
        country = data.optString("country");
        region = data.optString("region");
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }
}
