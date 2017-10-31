package yahooweather.jsorgensen.com.yahooweatherapi.data;

import org.json.JSONObject;

/**
 * Created by MECH on 10/31/2017.
 */

public class CImage implements JSONPopulator {

    private String url;

    @Override
    public void populate(JSONObject data) {
        url = data.optString("url");
    }

    public String getUrl() {
        return url;
    }
}
