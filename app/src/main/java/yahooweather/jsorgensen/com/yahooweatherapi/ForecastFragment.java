package yahooweather.jsorgensen.com.yahooweatherapi;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import yahooweather.jsorgensen.com.yahooweatherapi.data.Forecast;
import yahooweather.jsorgensen.com.yahooweatherapi.data.ForecastDay;

/**
 * Created by MECH on 10/31/2017.
 */

public class ForecastFragment extends Fragment {

    private MainActivity activity;
    private ViewGroup baseview;
    private LinearLayout forecastLayout;

    private Forecast forecast;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (MainActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        baseview = activity.gScrollView(null);
        return baseview;
    }

    public ViewGroup getBaseview(){
        return baseview;
    }

    public void setForecast(Forecast forecast){
        this.forecast = forecast;

        if(forecastLayout == null)
            forecastLayout = activity.gVerticalLayout(baseview);
        else
            forecastLayout.removeAllViews();

        for(int i=0; i<forecast.length; i++){
            gDayGroup(forecast.getDay(i));
        }
    }

    private LinearLayout gDayGroup(ForecastDay day){
        LinearLayout dayLayout = activity.gVerticalLayout(forecastLayout);

        LinearLayout date = activity.gHorizontalLayout(dayLayout);

        activity.gTextView(day.getDay(), date);
        activity.gTextView(day.getDate(), date);

        LinearLayout temps = activity.gHorizontalLayout(dayLayout);

        activity.gTextView("High: " + day.getHigh(), temps);
        activity.gTextView("Low: " + day.getLow(), temps);

        activity.gTextView(day.getText(), dayLayout);

        return dayLayout;
    }
}
