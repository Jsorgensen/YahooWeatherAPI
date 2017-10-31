package yahooweather.jsorgensen.com.yahooweatherapi.data;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by MECH on 10/31/2017.
 */

public class Forecast implements JSONPopulator {

    private Day[] days;

    @Override
    public void populate(JSONObject data) {
        try{
            JSONArray jsonArray = new JSONArray();
            days = new Day[jsonArray.length()];

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject j = jsonArray.getJSONObject(i);
                Day d = new Day();
                d.setCode(j.optInt("code"));
                d.setDate(j.optString("date"));
                d.setDay(j.optString("day"));
                d.setHigh(j.optInt("high"));
                d.setLow(j.optInt("low"));
                d.setText(j.optString("text"));
                days[i] = d;
            }
        }catch(Exception e){

        }
    }

    public Day[] getDays() {
        return days;
    }

    public Day getDay(int index){
        return days[index];
    }

    public class Day{
        private int code;
        private String date, day;
        private int high, low;
        private String text;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public int getHigh() {
            return high;
        }

        public void setHigh(int high) {
            this.high = high;
        }

        public int getLow() {
            return low;
        }

        public void setLow(int low) {
            this.low = low;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
