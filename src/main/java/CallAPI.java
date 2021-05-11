

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is used to call the API and append its data to a response
 * It also holds the fields name along with objects in the API json file represented by string variables.
 * @author Daniel De Lima
 * @version 1.0
 */
public class CallAPI {

    // ---- Json Object ----
    private String main;

    // ---- Json Array -----
    private String weather;

    // ---- Json keys -------
    private String cityName;
    private String temperature;
    private String maxTemp;
    private String minTemp;
    private String latitude;
    private String longitude;
    private String windSpeed;
    private String description;
    private String humidity;


    //---- Getters and Setters
    public String getMain (){
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    //Constructor
    public CallAPI(){
        super();
    }

    /**
     * This method creates the url from the API website
     * @param zipCode represents the zip code input from user
     * @return  OpenWeatherMapApi URL
     */
    public String composeCall(String zipCode) {

        final String API_KEY = "";

         //country code is locked to be US only
        final String COUNTRY_CODE = "US";

        return "http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + "," + COUNTRY_CODE + "&appid=" + API_KEY;
    }

    /**
     * This method gets the data from the API based on the zip code formed with the url
     * @param url represents the url from OpenWeatherMapApi utilized to get weather data
     * @return response with api data
     * @throws IOException in case of interrupted io operation
     */
    public static StringBuilder makeApiCall(String url) throws IOException {

        //creating url from
        URL object = new URL(url);

        //define  connection to make api request
        HttpURLConnection connection = (HttpURLConnection) object.openConnection();

        //read data from input stream
        BufferedReader bfr = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));

        //will have data that was read from buffered reader
        String inputLine;

        //will be used for appending data from input line
        StringBuilder response = new StringBuilder();

        //appending data to response
        while ((inputLine = bfr.readLine()) != null) {
            response.append(inputLine);
        }

        //closing buffered reader
        bfr .close();

        return response;
    }
}
