package main_package;

import org.json.JSONObject;
import java.util.ArrayList;

/**
 * This class is used to add all the data into a data structure (Array List)
 * @author Daniel De Lima
 * @version 1.0
 */
public class AddWeatherData {

    //constructor
    public AddWeatherData(){
        super();
    }

    //creating Return API Data object
    private static ReturnWeatherData rWeatherData = new ReturnWeatherData();


    /**
     * This method adds the response with weather data to a Array list
     * @param response response with JSon data from API url
     * @return Array list with the weather data
     */
    public static ArrayList<String> addSpecificInfo(StringBuilder response) {

        //array list that will store the weather data
        ArrayList<String> weatherArrayList = new ArrayList<>();

        //json object containing the response data
        JSONObject responseData = new JSONObject(response.toString());

        /*Putting City Name*/
        String cityName = rWeatherData.returnCityName(responseData);
        weatherArrayList.add("City Name: " + cityName);

        /*Putting Current Temperature*/
        String temperature = rWeatherData.returnTemperature(responseData);
        weatherArrayList.add("Temp: " + temperature + "°F");

        /*Putting Max Temperature*/
        String maxTemp = rWeatherData.returnHighestTemperature(responseData);
        weatherArrayList.add("Temp Max: " + maxTemp + "°F");

        /*Putting Min Temperature*/
        String minTemp = rWeatherData.returnLowestTemperature(responseData);
        weatherArrayList.add( "Temp Min: " + minTemp + "°F");

        /*Putting Description*/
        String description = rWeatherData.returnDescription(responseData);
        weatherArrayList.add("Description: "  + description);

        /*Putting Humidity*/
        JSONObject field = responseData.getJSONObject("main");
        String humidity = rWeatherData.returnHumidity(field);
        weatherArrayList.add("Humidity: "  + humidity + "%");

        /*Putting Latitude and Longitude*/
        JSONObject field2 = responseData.getJSONObject("coord");

        //defining latitude and longitude variables
        String longitude,
                latitude;

        //assigning value to latitude and longitude variables
        longitude = rWeatherData. returnCityLon(field2);
        latitude = rWeatherData.returnCityLat(field2);

        weatherArrayList.add( "Longitude: "  + longitude);
        weatherArrayList.add("Latitude: " + latitude);

        /*Putting Wind Speed*/
        JSONObject field3 = responseData.getJSONObject("wind");
        String wind = rWeatherData.returnWindSpeed(field3);
        weatherArrayList.add( "Wind speed: " + wind + " mph");

        return weatherArrayList;
    }
}
