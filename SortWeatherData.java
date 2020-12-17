package main_package;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is used to sort the output of (sorted weather history) option
 * @author Daniel De Lima
 * @version 1.0
 */
public class SortWeatherData {

    /**
     * This method sorts the array list data by code point
     * @param list array list with weather data
     */
    public static void insertionSort(ArrayList < String > list) {

        for (int i = 1; i < list.size(); i++) {
            String key = list.get(i);
            int j = i - 1;
            while (j >= 0 && key.compareTo(list.get(j)) < 0) {
                //swap indexes
                Collections.swap(list, j + 1, j);
                j--;
            }
            list.set(j + 1, key);
        }
    }
}