package main_package;

/**************************************************************
 * Name        : Zip Code Weather App
 * Author      : Daniel De Lima
 * Created     : 12/17/2020
 * Course      : CIS 152 Data Structures
 * Version     : 1.0
 * OS          : Windows 10
 * Copyright   : This is my own original work based on
 *               specifications issued by our instructor
 *
 * Description : This program takes in a zip code and returns weather data based on the zip code
 *
 *               Input:  if 50320 is provided as zip code
 *
 *               Output: current weather information such as:
 *               current temperature, weather description, latitude, longitude
 *               highest temperature, lowest temperature, wind speed, city name for tha zip code.
 *
 * Academic Honesty: I attest that this is my original work.
 * I have not used unauthorized source code, either modified or 
 * unmodified. I have not given other fellow student(s) access to
 * my program.         
 ***************************************************************/

import javax.swing.JFrame;

/**
 * This class initializes the weather app
 */
public class StartWeatherApp {
    public static void main(String[] args) {
        GUI weatherApp = new GUI();

        //defining GUI properties
        weatherApp.setSize(910, 500);
        weatherApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        weatherApp.setVisible(true);
    }
}