package yahooweather.jsorgensen.com.yahooweatherapi.data;

import org.json.JSONObject;

/**
 * Created by MECH on 10/30/2017.
 */
public class Condition implements JSONPopulator{

    private int code, temperature;
    private String date, description;

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        date = data.optString("date");
        temperature = data.optInt("temp");
        description = data.optString("text");
    }

    public int getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }
}
