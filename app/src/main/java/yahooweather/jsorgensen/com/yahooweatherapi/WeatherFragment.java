package yahooweather.jsorgensen.com.yahooweatherapi;

import android.app.Fragment;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by MECH on 10/31/2017.
 */

public class WeatherFragment extends Fragment implements View.OnClickListener{

    private MainActivity activity;
    private ViewGroup baseview;
    private LinearLayout foundation_view;
    private LinearLayout horizontal_sub;
    private LinearLayout left_sub, right_sub;
    private LinearLayout weather_group, wind_group, atmosphere_group, astronomy_group, location_group;
    private LinearLayout switch_group;
    private TextView temperature, condition, location, time;
    private TextView atmosphere_title, humidity, pressure, visibility;
    private TextView location_title, latitude, longitude;
    private TextView wind_title, wind_chill, wind_speed;
    private TextView astronomy_title, sunrise, sunset;
    private TextView city_title, state_title;
    private EditText city, state;
    private Button switch_location;
    private Button view_forecast;

    private int width, height;
    private enum ButtonReference{Search, ForecastView};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (MainActivity)getActivity();

        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        width = size.x;
        height = size.y;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            width /= 2;

        baseview = activity.gScrollView(null);

        foundation_view = activity.gVerticalLayout(baseview);

        weather_group = gVerticalLayout(foundation_view);
        activity.setLayoutParams(weather_group, foundation_view, LinearLayout.LayoutParams.MATCH_PARENT, null);

        temperature = gMainTitle("Temperature", weather_group);
        condition = gTextView("Condition", weather_group);
        location = gTextView("Location", weather_group);
        time = gTextView("Time", weather_group);

        horizontal_sub = activity.gHorizontalLayout(foundation_view);

        left_sub = activity.gVerticalLayout(horizontal_sub);

        atmosphere_group = gVerticalLayout(left_sub);

        atmosphere_title = gTitle("Atmosphere", atmosphere_group);
        humidity = gTextView("Humidity", atmosphere_group);
        pressure = gTextView("Pressure", atmosphere_group);
        visibility = gTextView("Visibility", atmosphere_group);

        location_group = gVerticalLayout(left_sub);

        location_title = gTitle("Location", location_group);
        latitude = gTextView("Latitude", location_group);
        longitude = gTextView("Longitude", location_group);

        right_sub = activity.gVerticalLayout(horizontal_sub);

        wind_group = gVerticalLayout(right_sub);

        wind_title = gTitle("Wind", wind_group);
        wind_chill = gTextView("Chill", wind_group);
        wind_speed = gTextView("Speed", wind_group);

        astronomy_group = gVerticalLayout(right_sub);

        astronomy_title = gTitle("Astronomy", astronomy_group);
        sunrise = gTextView("Sunrise", astronomy_group);
        sunset = gTextView("Sunset", astronomy_group);

        switch_group = gHorizontalLayout(foundation_view);
        activity.setLayoutParams(switch_group, foundation_view, LinearLayout.LayoutParams.MATCH_PARENT, null);

        city_title = gTextView("City: ", switch_group);
        city = gEditText("Enter City", switch_group);
        city.setMinWidth(activity.getTextSize() * 6);
        state_title = gTextView(",  State: ", switch_group);
        state = gEditText("Enter State", switch_group);
        state.setMinWidth(activity.getTextSize() * 2);
        switch_location = gButton("\uD83D\uDD0D", switch_group, ButtonReference.Search);
        activity.setMargins(switch_location, 15, null, null, null);

        view_forecast = gButton("View nth Day Forecast", foundation_view, ButtonReference.ForecastView);
        int padding = 30;
        view_forecast.setPadding(padding, padding, padding, padding);
        int margins = 30;
        activity.setMargins(view_forecast, margins, margins, margins, margins);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return baseview;
    }

    @Override
    public void onClick(View view) {
        ButtonReference button_reference = (ButtonReference) view.getTag();
        String ref = button_reference.toString();
        switch(ref){
            case("Search"):
                String location = city.getText().toString() + ", " + state.getText().toString();
                activity.refreshWeather(location);
                break;

            case("ForecastView"):
                activity.switchToForecast();
                break;
        }
    }

    public ViewGroup getBaseview(){
        return baseview;
    }

    public void setTemperatureText(String text){
        temperature.setText(text);
    }
    public void setConditionText(String text){
        condition.setText(text);
    }
    public void setLocationText(String text){
        location.setText(text);
    }
    public void setTimeText(String text) {time.setText(text);}

    public void setHumidity(String text) {humidity.setText(text);}
    public void setPressure(String text) {pressure.setText(text);}
    public void setVisibity(String text) {visibility.setText(text);}

    public void setLatitude(String text) {latitude.setText(text);}
    public void setLongitude(String text) {longitude.setText(text);}

    public void setWindChill(String text) {wind_chill.setText(text);}
    public void setWindSpeed(String text) {wind_speed.setText(text);}

    public void setSunrise(String text) {sunrise.setText(text);}
    public void setSunset(String text) {sunset.setText(text);}

    public void setCity(String text) {city.setText(text);}
    public void setState(String text) {state.setText(text);}

    public void setViewForecastDays(String text) {view_forecast.setText("View " + text + " Day Forecast");}

    public LinearLayout gHorizontalLayout(ViewGroup parent){
        LinearLayout l = activity.gHorizontalLayout(parent);
        activity.setLayoutParams(l, parent, width/2-40, null);
        int padding = 15;
        l.setPadding(padding, padding, padding, padding);
        int margins = 15;
        activity.setMargins(l, margins, margins, margins, margins);
        int background_color = ResourcesCompat.getColor(getResources(), R.color.sub_layout, null)
                , border_color = ResourcesCompat.getColor(getResources(), R.color.sub_border, null);
        activity.setBorder(l, background_color, 5, border_color, 30);

        return l;
    }

    public LinearLayout gVerticalLayout(ViewGroup parent){
        LinearLayout l = gHorizontalLayout(parent);

        l.setOrientation(LinearLayout.VERTICAL);

        return l;
    }

    public TextView gTextView(String text, ViewGroup parent){
        TextView t = activity.gTextView(text, parent);

        return t;
    }

    public TextView gMainTitle(String text, ViewGroup parent){
        TextView t = activity.gTextView(text, parent);
        t.setTextSize(TypedValue.COMPLEX_UNIT_PX, activity.getTextSize()*2);
        t.setTextColor(ResourcesCompat.getColor(getResources(), R.color.temperature_title, null));

        return t;
    }

    public TextView gTitle(String text, ViewGroup parent){
        TextView t = activity.gTextView(text, parent);
        t.setTextSize(TypedValue.COMPLEX_UNIT_PX, activity.getTextSize()*5/4);
        t.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sub_titles, null));

        return t;
    }

    public EditText gEditText(String hint, ViewGroup parent){
        EditText e = activity.gEditText(hint, parent);
        int background_color = ResourcesCompat.getColor(getResources(), R.color.edit_text, null)
                , border_color = ResourcesCompat.getColor(getResources(), R.color.sub_border, null);
        activity.setBorder(e, background_color, 0, border_color, 15);

        return e;
    }

    public Button gButton(String hint, ViewGroup parent, ButtonReference reference){
        Button b = activity.gButton(hint, parent);
        int background_color = ResourcesCompat.getColor(getResources(), R.color.button, null)
                , border_color = ResourcesCompat.getColor(getResources(), R.color.sub_border, null);
        activity.setBorder(b, background_color, 0, border_color, 30);
        setOnClickListener(b, reference);

        return b;
    }

    public void setOnClickListener(Button button, ButtonReference reference){
        button.setOnClickListener(this);
        button.setTag(reference);
    }
}
