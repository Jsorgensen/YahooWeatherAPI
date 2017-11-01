package yahooweather.jsorgensen.com.yahooweatherapi;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by MECH on 10/31/2017.
 */

public class WeatherFragment extends Fragment {

    private MainActivity activity;
    private ViewGroup baseview;
    private TextView temperature, condition, location;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (MainActivity)getActivity();

        temperature = activity.gTextView("Temperature", baseview);
        condition = activity.gTextView("Condition", baseview);
        location = activity.gTextView("Location", baseview);
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

    public void setTemperatureText(String text){
        temperature.setText(text);
    }

    public void setConditionText(String text){
        condition.setText(text);
    }

    public void setLocationText(String text){
        location.setText(text);
    }
}
