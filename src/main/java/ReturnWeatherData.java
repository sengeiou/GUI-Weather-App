
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class is used to return weather data from the json file coming from the API
 * @author Daniel De Lima
 * @version 1.0
 */
public class ReturnWeatherData {

    //creating new Call API object
    CallAPI callObj = new CallAPI();

    /**
     * Returns a city name
     * @param jsonObj represents json object containing data from API response
     * @return data from the json object corresponding to the city name
     */
    protected  String returnCityName(JSONObject jsonObj) {

        //gets data from json key (name)
        callObj.setCityName("name");

        //assigns to a variable and returns
        return jsonObj.getString(callObj.getCityName());
    }

    /**
     * Returns a the current temperature in a city
     * @param jsonObj represents json object containing data from API response
     * @return data from the json object corresponding to the current temperature
     */
    protected  String returnTemperature(JSONObject jsonObj) {

        //gets data from json object (main)
        callObj.setMain("main");
        JSONObject jsonField = jsonObj.getJSONObject(callObj.getMain());

        //gets data from key in main object (temp)
        callObj.setTemperature("temp");
        double jsonTempValue;

        //assigns to a variable and returns
        jsonTempValue = jsonField.getDouble(callObj.getTemperature());
        return DataConversion.convertToFahrenheit(jsonTempValue);
    }


    /**
     * Returns the longitude of a city
     * @param jsonObj represents json object containing data from API response
     * @return data from the json object corresponding to the city's longitude
     */
    protected  String returnCityLon(JSONObject jsonObj) {

        //gets data from json key (lon)
        callObj.setLongitude("lon");
        double jsonLonValue;

        //assigns to a variable and returns
        jsonLonValue = jsonObj.getDouble(callObj.getLongitude());
        return String.valueOf(jsonLonValue);
    }


    /**
     * Returns the latitude of a city
     * @param jsonObj represents json object containing data from API response
     * @return data from the json object corresponding to the city's latitude
     */
    protected  String returnCityLat(JSONObject jsonObj) {

        //gets data from json key (lat)
        callObj.setLatitude("lat");
        double jsonLatValue;

        //assigns to a variable and returns
        jsonLatValue = jsonObj.getDouble(callObj.getLatitude());
        return String.valueOf(jsonLatValue);
    }


    /**
     * Returns the current wind speed
     * @param jsonObj represents json object containing data from API response
     * @return data from the json object corresponding to the city's wind speed
     */
    protected  String returnWindSpeed(JSONObject jsonObj) {

        //gets data from json key (lat)
        callObj.setWindSpeed("speed");
        double jsonWindSpeedValue;

        //assigns to a variable and returns
        jsonWindSpeedValue = jsonObj.getDouble(callObj.getWindSpeed());
        return DataConversion.convertToMilesPerHour(jsonWindSpeedValue);
    }


    /**
     * Returns predicted highest temperature
     * @param jsonObj represents json object containing data from API response
     * @return data from the json object corresponding to the city's predicted highest temperature
     */
    protected  String returnHighestTemperature(JSONObject jsonObj) {

        //gets data from json object (main)
        callObj.setMain("main");
        JSONObject jsonField = jsonObj.getJSONObject(callObj.getMain());

        //gets data from json key (temp_max)
        callObj.setMaxTemp("temp_max");
        double jsonHighTempValue;

        //assigns to a variable and returns
        jsonHighTempValue = jsonField.getDouble(callObj.getMaxTemp());
        return DataConversion.convertToFahrenheit(jsonHighTempValue);
    }


    /**
     * Returns predicted lowest temperature
     * @param jsonObj represents json object containing data from API response
     * @return data from the json object corresponding to the city's lowest temperature
     */
    protected  String returnLowestTemperature(JSONObject jsonObj) {

        //gets data from json object (main)
        callObj.setMain("main");
        JSONObject jsonField = jsonObj.getJSONObject(callObj.getMain());

        //gets data from json key (temp_min)
        callObj.setMinTemp("temp_min");
        double jsonLowTempValue;

        //assigns to a variable and returns
        jsonLowTempValue = jsonField.getDouble(callObj.getMinTemp());
        return DataConversion.convertToFahrenheit(jsonLowTempValue);
    }


    /**
     * Returns current description of the weather
     * @param jsonObj represents json object containing data from API response
     * @return data from the json object corresponding to the city's current weather description
     */
    protected  String returnDescription(JSONObject jsonObj) {

        //gets data from json key (description)
        callObj.setDescription("description");
        JSONObject myResponse = new JSONObject(jsonObj.toString());

        //gets data from json array (weather)
        callObj.setWeather("weather");
        JSONArray array = new JSONArray(myResponse.getJSONArray(callObj.getWeather()));

        //gets data from the first index in the file (0) is only index available
        int indexPos = 0;

        //assigns to a variable and returns
        JSONObject jsonData = array.getJSONObject(indexPos);
        return jsonData.getString(callObj.getDescription());
    }


    /**
     * Returns current humidity rate of the weather
     * @param jsonObj represents json object containing data from API response
     * @return data from the json object corresponding to the city's current humidity rate
     */
    protected  String returnHumidity(JSONObject jsonObj) {

        //gets data from json key (description)
        callObj.setHumidity("humidity");
        int jsonHumidityValue;

        //assigns to a variable and returns
        jsonHumidityValue = jsonObj.getInt(callObj.getHumidity());
        return String.valueOf(jsonHumidityValue);
    }
}
