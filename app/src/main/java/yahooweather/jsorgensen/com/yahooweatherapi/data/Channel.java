package yahooweather.jsorgensen.com.yahooweatherapi.data;

import org.json.JSONObject;

/**
 * Created by MECH on 10/30/2017.
 */
public class Channel implements JSONPopulator {

    private Units units;
    private Item item;
    private CImage image;

    @Override
    public void populate(JSONObject data) {

        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));

        image = new CImage();
        image.populate(data.optJSONObject("image"));
    }

    public Units getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }

    public CImage getImage() {
        return image;
    }
}
