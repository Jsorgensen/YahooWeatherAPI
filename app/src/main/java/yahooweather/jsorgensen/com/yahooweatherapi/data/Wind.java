package yahooweather.jsorgensen.com.yahooweatherapi.data;

import org.json.JSONObject;

/**
 * Created by MECH on 10/31/2017.
 */

public class Wind implements JSONPopulator{

    int chill, direction, speed;

    @Override
    public void populate(JSONObject data) {
        chill = data.optInt("chill");
        direction = data.optInt("direction");
        speed = data.optInt("speed");
    }

    public int getChill() {
        return chill;
    }

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }
}
