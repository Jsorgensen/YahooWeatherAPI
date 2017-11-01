package yahooweather.jsorgensen.com.yahooweatherapi.data;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by MECH on 10/31/2017.
 */

public class Forecast implements JSONPopulator {

    private ForecastDay[] days;
    public int length;

    @Override
    public void populate(JSONObject data) {
        try{
            JSONArray jsonArray = new JSONArray();
            days = new ForecastDay[jsonArray.length()];

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject j = jsonArray.getJSONObject(i);
                ForecastDay d = new ForecastDay();
                d.setCode(j.optInt("code"));
                d.setDate(j.optString("date"));
                d.setDay(j.optString("day"));
                d.setHigh(j.optInt("high"));
                d.setLow(j.optInt("low"));
                d.setText(j.optString("text"));
                days[i] = d;
            }

            length = days.length;
        }catch(Exception e){

        }
    }

    public ForecastDay[] getDays() {
        return days;
    }

    public ForecastDay getDay(int index){
        return days[index];
    }
}
