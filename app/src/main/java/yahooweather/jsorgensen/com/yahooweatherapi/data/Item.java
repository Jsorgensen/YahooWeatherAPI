package yahooweather.jsorgensen.com.yahooweatherapi.data;

import org.json.JSONObject;

/**
 * Created by MECH on 10/30/2017.
 */
public class Item implements JSONPopulator{

    private String title;
    private Double latitude, longitude;
    private String link, pubDate;
    private Condition condition;
    private Forecast forecast;

    @Override
    public void populate(JSONObject data) {
        title = data.optString("title");

        latitude = data.optDouble("lat");

        longitude = data.optDouble("long");

        link = data.optString("link");

        pubDate = data.optString("pubDate");

        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));

        forecast = new Forecast();
        forecast.populate(data.optJSONArray("forecast"));
    }

    public Condition getCondition() {
        return condition;
    }

    public String getTitle() {
        return title;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public Forecast getForecast() {
        return forecast;
    }
}
