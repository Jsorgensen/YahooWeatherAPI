package yahooweather.jsorgensen.com.yahooweatherapi;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import yahooweather.jsorgensen.com.yahooweatherapi.data.Channel;
import yahooweather.jsorgensen.com.yahooweatherapi.services.WeatherServiceCallback;
import yahooweather.jsorgensen.com.yahooweatherapi.services.YahooWeatherService;


public class MainActivity extends Activity implements WeatherServiceCallback {

    private YahooWeatherService service;
    private ProgressDialog dialog;

    private ViewGroup baseview;
    private WeatherFragment weatherFragment;
    private ForecastFragment forecastFragment;

    private int textSize;
    private boolean switchToForecast;
    private int weatherColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseview = gHorizontalLayout(null);
        textSize = 120;
        switchToForecast = false;
        weatherColor = ResourcesCompat.getColor(getResources(), R.color.weatherColor, null);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Springville, UT");

        weatherFragment = new WeatherFragment();
        forecastFragment = new ForecastFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(weatherFragment);
        fragmentTransaction.show(forecastFragment);
        fragmentTransaction.commit();

        baseview.addView(weatherFragment.getBaseview());
    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        String temperature_text = channel.getItem().getCondition().getTemperature() + channel.getUnits().getTemperature().toString();
        String condition_text = channel.getItem().getCondition().getDescription().toString();
        String location_text = service.getLocation().toString();

        weatherFragment.setTemperatureText(temperature_text);
        weatherFragment.setConditionText(condition_text);
        weatherFragment.setLocationText(location_text);
    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        updateOrientation();
    }

    public void updateOrientation(){
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth()
                , height = display.getHeight();

        int threshold = 1080;
        int orientation = display.getOrientation();
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            if(switchToForecast) {
                if(forecastFragment.getBaseview().getParent() == null)
                    baseview.addView(forecastFragment.getBaseview());
                if(weatherFragment.getBaseview().getParent() != null)
                    baseview.removeView(weatherFragment.getBaseview());
            }else{
                if(forecastFragment.getBaseview().getParent() != null)
                    baseview.removeView(forecastFragment.getBaseview());
                if(weatherFragment.getBaseview().getParent() == null)
                    baseview.addView(weatherFragment.getBaseview());
            }
            /*
            weatherFragment.getBaseview().setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT
                            , LinearLayout.LayoutParams.WRAP_CONTENT
                            , 1.0f
                    )
            );
            forecastFragment.getBaseview().setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT
                            , LinearLayout.LayoutParams.WRAP_CONTENT
                            , 1.0f
                    )
            );
            */
        }else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            if(forecastFragment.getBaseview().getParent() == null)
                baseview.addView(forecastFragment.getBaseview());
            if(weatherFragment.getBaseview().getParent() == null)
                baseview.addView(weatherFragment.getBaseview(), 0);

            /*
            weatherFragment.getBaseview().setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT
                            , LinearLayout.LayoutParams.WRAP_CONTENT
                            , 1.0f
                    )
            );
            forecastFragment.getBaseview().setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT
                            , LinearLayout.LayoutParams.WRAP_CONTENT
                            , 1.0f
                    )
            );
            */
        }
    }

    protected LinearLayout gHorizontalLayout(ViewGroup parent){
        LinearLayout l = new LinearLayout(this);
        l.setId(l.generateViewId());
        setLayoutParams(l, parent);
        l.setGravity(Gravity.CENTER);
        if(parent != null)
            parent.addView(l);

        return l;
    }

    protected LinearLayout gVerticalLayout(ViewGroup parent){
        LinearLayout l = gHorizontalLayout(parent);
        l.setOrientation(LinearLayout.VERTICAL);

        return l;
    }

    protected ScrollView gScrollView (ViewGroup parent){
        ScrollView s = new ScrollView(this);
        s.setId(s.generateViewId());
        setLayoutParams(s, parent);
        if(parent != null)
            parent.addView(s);

        return s;
    }

    protected TextView gTextView(String text, ViewGroup parent){
        TextView t = new TextView(this);
        t.setId(t.generateViewId());
        setLayoutParams(t, parent);
        t.setText(text);
        t.setBackgroundColor(weatherColor);
        if(parent != null)
            parent.addView(t);

        return t;
    }

    protected Button gButton(String text, ViewGroup parent){
        Button b = new Button(this);
        b.setId(b.generateViewId());
        setLayoutParams(b, parent);
        b.setMinHeight(0);
        b.setMinHeight(0);
        b.setMinimumHeight(0);
        b.setMinimumWidth(0);
        b.setIncludeFontPadding(false);
        b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        b.setText(text);

        return b;
    }

    protected void setLayoutParams(View view, ViewGroup parent){
        setLayoutParams(view, parent, null, null);
    }

    protected void setLayoutParams(View view, ViewGroup parent, Integer width, Integer height){
        if(view == null)
            return;
        if(parent == null){
            return;
        }

        if(width == null)
            width = LinearLayout.LayoutParams.WRAP_CONTENT;
        if(height == null)
            height = LinearLayout.LayoutParams.WRAP_CONTENT;

        String parent_class = parent.getClass().getSimpleName();
        switch(parent_class){
            case("LinearLayout"):
                view.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                width,
                                height
                        )
                );
                break;

            case("ScrollView"):
                view.setLayoutParams(
                        new ScrollView.LayoutParams(
                                width
                                , height
                        )
                );
                break;
        }
    }
}
