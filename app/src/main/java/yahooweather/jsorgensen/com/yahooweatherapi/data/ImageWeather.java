package yahooweather.jsorgensen.com.yahooweatherapi.data;

import org.json.JSONObject;

/**
 * Created by MECH on 10/31/2017.
 */

public class ImageWeather implements JSONPopulator {

    private String title;
    private int width, height;
    private String link, url;

    @Override
    public void populate(JSONObject data) {
        title = data.optString("title");
        width = data.optInt("width");
        height = data.optInt("height");
        link = data.optString("link");
        url = data.optString("url");
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getLink() {
        return link;
    }

    public String getUrl() {
        return url;
    }
}
