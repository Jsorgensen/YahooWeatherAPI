package yahooweather.jsorgensen.com.yahooweatherapi.data;

import org.json.JSONObject;

/**
 * Created by MECH on 10/30/2017.
 */
public class Units  implements JSONPopulator{

    private String temperature;

    @Override
    public void populate(JSONObject data){
        temperature = data.optString("temperature");
    }

    public String getTemperature() {
        return temperature;
    }
}
