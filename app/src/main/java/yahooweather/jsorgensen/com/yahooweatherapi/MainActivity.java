package yahooweather.jsorgensen.com.yahooweatherapi;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
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

        textSize = 60;
        switchToForecast = false;
        weatherColor = ResourcesCompat.getColor(getResources(), R.color.weatherColor, null);

        baseview = gHorizontalLayout(null);
        ((LinearLayout)baseview).setGravity(Gravity.CENTER_VERTICAL);
        baseview.setBackground(getImage("hobble_creek"));
        setContentView(baseview);

        FragmentManager fm1 = getFragmentManager();
        FragmentTransaction ft1 = fm1.beginTransaction();
        weatherFragment = new WeatherFragment();
        ft1.add(baseview.getId(), weatherFragment);
        ft1.commit();

        FragmentManager fm2 = getFragmentManager();
        FragmentTransaction ft2 = fm2.beginTransaction();
        forecastFragment = new ForecastFragment();
        ft2.add(baseview.getId(), forecastFragment);
        ft2.commit();

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Springville, UT");
    }

    private void layout(){

    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        weatherFragment.setTemperatureText(channel.getItem().getCondition().getTemperature() + "°" + channel.getUnits().getTemperature().toString());
        weatherFragment.setConditionText(channel.getItem().getCondition().getDescription().toString());
        weatherFragment.setLocationText(service.getLocation().toString());
        weatherFragment.setTimeText(channel.getItem().getCondition().getDate());

        weatherFragment.setHumidity("Humidity: " + channel.getAtmosphere().getHumidity() + "%");
        weatherFragment.setPressure("Pressure: " + channel.getAtmosphere().getPressure() + channel.getUnits().getPressure());
        weatherFragment.setVisibity("Visibility: " + channel.getAtmosphere().getVisibility() + channel.getUnits().getDistance());

        weatherFragment.setLatitude("Lat: " + channel.getItem().getLatitude() + "°");
        weatherFragment.setLongitude("Long: " + channel.getItem().getLongitude() + "°");

        weatherFragment.setWindChill("Chill: " + channel.getWind().getChill() + "°" + channel.getUnits().getTemperature());
        weatherFragment.setWindSpeed("Speed: " + channel.getWind().getSpeed() + channel.getUnits().getSpeed());

        weatherFragment.setSunrise("Sunrise: " + channel.getAstronomy().getSunrise());
        weatherFragment.setSunset("Sunset: " + channel.getAstronomy().getSunset());

        weatherFragment.setCity(channel.getLocation().getCity());
        weatherFragment.setState(channel.getLocation().getRegion());

        int days = channel.getItem().getForecast().length;
        weatherFragment.setViewForecastDays(channel.getItem().getForecast().length + "");


        forecastFragment.setUnit("°" + channel.getUnits().getTemperature());
        forecastFragment.setForecast(channel.getItem().getForecast());
    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    public void refreshWeather(String location){
        service.refreshWeather(location);
    }

    public ViewGroup getBaseView(){
        return baseview;
    }

    public void addView(View view){
        baseview.addView(view);
    }

    public int getTextSize() {
        return textSize;
    }

    public void switchToForecast(){
        if(forecastFragment.getBaseview().getParent() == null)
            baseview.addView(forecastFragment.getBaseview());

        baseview.removeView(weatherFragment.getBaseview());
        baseview.addView(weatherFragment.getBaseview());
    }

    public void switchToWeather(){
        if(weatherFragment.getBaseview().getParent() == null)
            baseview.addView(weatherFragment.getBaseview());

        baseview.removeView(forecastFragment.getBaseview());
        baseview.addView(forecastFragment.getBaseview());
    }

    public BitmapDrawable getImage(String filename){
        int i = getResources().getIdentifier("@drawable/"+filename, null, getPackageName());
        Drawable d = getResources().getDrawable(i);
        Bitmap b = ((BitmapDrawable)d).getBitmap();
        BitmapDrawable bd = new BitmapDrawable(b);
        bd.setTargetDensity(b.getDensity());
        bd.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        return bd;
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

    protected EditText gEditText(String hint, ViewGroup parent){
        EditText e = new EditText(this);
        e.setId(e.generateViewId());
        setLayoutParams(e, parent);
        e.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.edit_text, null));
        e.setPadding(0, 0, 0, 0);
        e.setIncludeFontPadding(false);
        setMargins(e, 0, 0, 0, 0);
        e.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        if(parent != null)
            parent.addView(e);

        return e;
    }

    protected TextView gTextView(String text, ViewGroup parent){
        TextView t = new TextView(this);
        t.setId(t.generateViewId());
        setLayoutParams(t, parent);
        t.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        t.setText(text);
        t.setTextColor(ResourcesCompat.getColor(getResources(), R.color.textColor, null));
        t.setBackgroundColor(weatherColor);
        if(parent != null)
            parent.addView(t);

        return t;
    }

    protected Button gButton(String text, ViewGroup parent){
        Button b = new Button(this);
        b.setId(b.generateViewId());
        setLayoutParams(b, parent);
        b.setPadding(15, 0, 15, 0);
        b.setMinHeight(0);
        b.setMinHeight(0);
        b.setMinimumHeight(0);
        b.setMinimumWidth(0);
        b.setIncludeFontPadding(false);
        b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        b.setText(text);
        b.setTransformationMethod(null);
        b.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.button, null));
        if(parent != null)
            parent.addView(b);

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

    protected void setMargins(View view, Integer left, Integer top, Integer right, Integer bottom){
        ViewGroup parent = (ViewGroup)view.getParent();
        if(parent == null)
            return;

        int l=0, t=0, r=0, b=0;
        if(left != null)
            l = left;
        if(top != null)
            t = top;
        if(right != null)
            r = right;
        if(bottom != null)
            b = bottom;

        String parent_class = parent.getClass().getSimpleName();
        switch(parent_class){
            case("LinearLayout"):
                LinearLayout.LayoutParams lllp = (LinearLayout.LayoutParams)view.getLayoutParams();

                if(left == null)
                    l = lllp.leftMargin;
                if(top == null)
                    t = lllp.topMargin;
                if(right == null)
                    r = lllp.rightMargin;
                if(bottom == null)
                    b = lllp.bottomMargin;

                lllp.setMargins(l, t, r, b);
                break;

            case("FrameLayout"):
            case("HorizontalScrollView"):
            case("ScrollView"):
                FrameLayout.LayoutParams fllp = (FrameLayout.LayoutParams)view.getLayoutParams();

                if(left == null)
                    l = fllp.leftMargin;
                if(top == null)
                    t = fllp.topMargin;
                if(right == null)
                    r = fllp.rightMargin;
                if(bottom == null)
                    b = fllp.bottomMargin;

                fllp.setMargins(l, t, r, b);
                break;
        }
    }

    public void setBorder(View view, int background_color, int border_size, int border_color, int border_radius){
        GradientDrawable g = new GradientDrawable();
        g.setColor(background_color);
        g.setStroke(border_size, border_color);
        g.setGradientRadius(border_radius);
        g.setCornerRadius(border_radius);
        view.setBackground(g);
    }
}
