package yahooweather.jsorgensen.com.yahooweatherapi.data;

import org.json.JSONObject;

import java.net.URL;

/**
 * Created by MECH on 10/30/2017.
 */
public class Channel implements JSONPopulator {

    private Units units;
    private Item item;
    private ImageWeather image;
    private String title;
    private String link_string;
    private URL link_url;
    private String description;
    private String language;
    private String last_build_date;
    private int ttl;
    private LocationWeather location;
    private Wind wind;
    private Atmosphere atmosphere;
    private Astronomy astronomy;
    private ImageWeather imageWeather;


    @Override
    public void populate(JSONObject data) {

        units = new Units();
        units.populate(data.optJSONObject("units"));

        title = data.optString("title");

        link_string = data.optString("link");
        try {
            link_url = new URL(link_string);
        }catch(Exception e){

        }

        description = data.optString("description");

        language = data.optString("language");

        last_build_date = data.optString("lastBuildDate");

        ttl = data.optInt("ttl");

        location = new LocationWeather();
        location.populate(data.optJSONObject("location"));

        wind = new Wind();
        wind.populate(data.optJSONObject("wind"));

        atmosphere = new Atmosphere();
        atmosphere.populate(data.optJSONObject("atmosphere"));

        astronomy = new Astronomy();
        astronomy.populate(data.optJSONObject("astronomy"));

        image = new ImageWeather();
        image.populate(data.optJSONObject("image"));

        item = new Item();
        item.populate(data.optJSONObject("item"));
    }

    public Units getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }

    public ImageWeather getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getLink_string() {
        return link_string;
    }

    public URL getLink_url() {
        return link_url;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getLast_build_date() {
        return last_build_date;
    }

    public int getTtl() {
        return ttl;
    }

    public LocationWeather getLocation() {
        return location;
    }

    public Wind getWind() {
        return wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public ImageWeather getImageWeather() {
        return imageWeather;
    }
}
