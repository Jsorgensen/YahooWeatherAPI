package yahooweather.jsorgensen.com.yahooweatherapi.data;

import org.json.JSONObject;

/**
 * Created by MECH on 10/30/2017.
 */
public class Item implements JSONPopulator{

    private Condition condition;

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
    }

    public Condition getCondition() {
        return condition;
    }
}
