package yahooweather.jsorgensen.com.yahooweatherapi.services;

import yahooweather.jsorgensen.com.yahooweatherapi.data.Channel;

/**
 * Created by MECH on 10/30/2017.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
