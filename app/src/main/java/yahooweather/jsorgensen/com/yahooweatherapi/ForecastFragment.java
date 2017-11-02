package yahooweather.jsorgensen.com.yahooweatherapi;

import android.app.Fragment;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import yahooweather.jsorgensen.com.yahooweatherapi.data.Forecast;
import yahooweather.jsorgensen.com.yahooweatherapi.data.ForecastDay;

/**
 * Created by MECH on 10/31/2017.
 */

public class ForecastFragment extends Fragment implements View.OnClickListener{

    private MainActivity activity;
    private ViewGroup baseview;
    private LinearLayout foundation_view, forecast_layout;
    private Button back_top, back_bottom;

    private Forecast forecast;

    private int width, height;
    private enum ButtonReference{WeatherView};

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

        back_top = gButton("View Weather Forecast", foundation_view, ButtonReference.WeatherView);

        forecast_layout = gVerticalLayout(foundation_view);

        back_bottom = gButton("View Weather Forecast", foundation_view, ButtonReference.WeatherView);
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
            case("WeatherView"):
                activity.switchToWeather();
                break;
        }
    }

    public ViewGroup getBaseview(){
        return baseview;
    }

    public void setForecast(Forecast forecast){
        this.forecast = forecast;

        forecast_layout.removeAllViews();

        for(int i=0; i<forecast.length; i++){
            gDayGroup(forecast.getDay(i));
        }
    }

    private LinearLayout gDayGroup(ForecastDay day){
        LinearLayout dayLayout = gVerticalLayout(forecast_layout);

        LinearLayout date = gHorizontalLayout(dayLayout);

        gTextView(day.getDay(), date);
        gTextView(day.getDate(), date);

        LinearLayout temps = gHorizontalLayout(dayLayout);

        gTextView("High: " + day.getHigh(), temps);
        gTextView("Low: " + day.getLow(), temps);

        gTextView(day.getText(), dayLayout);

        return dayLayout;
    }

    public LinearLayout gHorizontalLayout(ViewGroup parent){
        LinearLayout l = activity.gHorizontalLayout(parent);
        int background_color = ResourcesCompat.getColor(getResources(), R.color.forecast_group, null)
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

    public Button gButton(String hint, ViewGroup parent, ButtonReference reference){
        Button b = activity.gButton(hint, parent);
        setOnClickListener(b, reference);

        return b;
    }

    private void setOnClickListener(Button button, ButtonReference reference){
        button.setOnClickListener(this);
        button.setTag(reference);
    }
}
