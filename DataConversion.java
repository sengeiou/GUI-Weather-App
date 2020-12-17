package main_package;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * This class is used to change the weather data from the starting format
 */
public class DataConversion {

    /**
     * This method converts temperature from Kelvin to fahrenheit.
     *
     * @param temp represents the kelvin data first passed
     * @return temp in form of fahrenheit
     */
    protected static String convertToFahrenheit(double temp) {

        //kelvin unit data to perform calculation
        final double KELVIN_UNIT = 273.15;

        //formula to convert kelvin to fahrenheit
        temp = (temp - KELVIN_UNIT) * 9 / 5 + 32;

        //rounding down to make temperature more accurate
        DecimalFormat df = new DecimalFormat("##");
        df.setRoundingMode(RoundingMode.DOWN);

        //returning temperature in fahrenheit
        return String.valueOf(df.format(temp));
    }


    /**
     * This method converts Meters per second (m/s) to Miles per hour (mph)
     * @param temp represents m/s data
     * @return wind speed converted to MPH
     */
    protected static String convertToMilesPerHour(double temp) {

        //MPH unit to perform calculation
        final double MPH_UNIT = 2.237;

        //simple calculation formula
        temp = temp * MPH_UNIT;

        //rounding down to make miles per hour more accurate
        DecimalFormat df = new DecimalFormat("##");
        df.setRoundingMode(RoundingMode.DOWN);

        //returning temperature in MPH
        return String.valueOf(df.format(temp));
    }

    /**
     * This method formats the output of the map data structure
     * @param map map with city name(key) and zip code (value)
     * @return a formatted output of the map
     */
    protected static String formatMapElements(Map<String, String> map){

        return map.toString().replace("=", " ").
                replace("{", "").
                replace("}", "").
                replace(",", "");
    }
}