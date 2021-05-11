import java.awt.GridLayout ;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 * This class implements the GUI for the weather app
 */
public class GUI extends JFrame {

    //Creating Call API object
    CallAPI callObj = new CallAPI();

    //Array list holds overall data | map holds city name and Zip Code.
    ArrayList < String > arraylist = new ArrayList<>();
    Map<String, String> map = new HashMap<>();

    // ---- Class components ----

    //provides area to type in zip code
    JTextField zipCodeField;

    //provides area to check current weather data
    JTextArea outputTextArea;

    //provides a way to submit the sip code to the url api
    //clear button provides a way to reset every component to its default state
    JButton zipSubButton,
            clearButton;

    //radios used to provide different weather app features
    JRadioButton descriptionRadio,
            temperatureRadio,
            humidityRadio,
            basicInfoRadio,
            extraRadio,
            citiesCheckRadio;

    //upper panel contains the 2 primary buttons along with the zip code text field
    //lower panel contains the radio buttons alon with the output text area
    JPanel upperPanel,
            lowerPanel;

    //creating new button group object to add in radio buttons
    ButtonGroup group = new ButtonGroup();

    //constructor
    public GUI() {

        //setting layout of frame
        setLayout(new GridLayout(2, 1));

        //------------------------------
        //--------- Upper Panel --------
        //------------------------------

        //initializing panel
        upperPanel = new JPanel();
        upperPanel.setBounds(0, 0, 700, 180);
        upperPanel.setBackground(new Color(0x2d3142));

        //initializing components for upper panel
        zipCodeField = new JTextField(10);
        zipCodeField.setSize(50, 40);
        zipSubButton = new JButton("Submit");
        clearButton = new JButton("Clear");

        //adding submission listener to submit button
        SubmitZipCodeListener szpl = new SubmitZipCodeListener();
        zipSubButton.addActionListener(szpl);

        ClearButtonListener cbl = new ClearButtonListener();
        clearButton.addActionListener(cbl);

        //adding components to panel
        upperPanel.add(zipCodeField);
        upperPanel.add(zipSubButton);
        upperPanel.add(clearButton);

        //------------------------------
        //--------- Lower Panel --------
        //------------------------------

        //initializing panel
        lowerPanel = new JPanel();
        lowerPanel.setBounds(0, 180, 700, 240);

        //initializing components for lower panel
        descriptionRadio = new JRadioButton("View weather description");
        temperatureRadio = new JRadioButton("View current temperature");
        humidityRadio = new JRadioButton("View humidity rate");
        basicInfoRadio = new JRadioButton("View current weather data");
        extraRadio = new JRadioButton("View complete weather data");
        citiesCheckRadio = new JRadioButton("View zip codes history");
        outputTextArea = new JTextArea(9, 10);

        //adding output listeners to radio buttons
        OutputListener ol = new OutputListener();
        descriptionRadio.addActionListener(ol);
        temperatureRadio.addActionListener(ol);
        humidityRadio.addActionListener(ol);
        basicInfoRadio.addActionListener(ol);
        extraRadio.addActionListener(ol);
        citiesCheckRadio.addActionListener(ol);

        //make only one radio clickable at a time
        group.add(descriptionRadio);
        group.add(temperatureRadio);
        group.add(humidityRadio);
        group.add(basicInfoRadio);
        group.add(extraRadio);
        group.add(citiesCheckRadio);

        //setting layout and adding components
        lowerPanel.setLayout(new GridLayout(7, 1));
        lowerPanel.add(descriptionRadio);
        lowerPanel.add(temperatureRadio);
        lowerPanel.add(humidityRadio);
        lowerPanel.add(basicInfoRadio);
        lowerPanel.add(extraRadio);
        lowerPanel.add(citiesCheckRadio);
        lowerPanel.add(outputTextArea);

        //adding panels
        add(upperPanel);
        add(lowerPanel);
    }

    /**
     * This class sends the data of the zip code to the api, as well as performing error handling
     */
    class SubmitZipCodeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //only call api if submit button is pressed and has valid zip code data
            if (e.getSource() == zipSubButton) {

                //assigning zip code data to string variable
                String zipCode = zipCodeField.getText();

                //assigning API url to string variable with zip code provided
                String url = callObj.composeCall(zipCode);

                //making call to the api using url above and adding to the array list
                try {
                    StringBuilder contents = CallAPI.makeApiCall(url);
                    arraylist = AddWeatherData.addSpecificInfo(contents);


                    //if zip code is not found then warn the user
                } catch(FileNotFoundException fex) {

                    JOptionPane.showMessageDialog(null, "Following zip code: " + zipCodeField.getText() + " was not found.");

                    //if there is no input then warn the user
                } catch(IOException io) {
                    JOptionPane.showMessageDialog(null, "Please enter a Zip Code!");
                }
            }
        }
    }

    /**
     * This class displays the weather data if a radio button is clicked
     */
    class OutputListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                //sorting the array list
                SortWeatherData.insertionSort(arraylist);

                //assigns description of the weather to the output area
                if (e.getSource() == descriptionRadio) {
                    outputTextArea.setText(arraylist.get(1));
                }

                //assigns current temperature to the output area
                if (e.getSource() == temperatureRadio) {
                    outputTextArea.setText(arraylist.get(7));
                }

                //assigns humidity of the weather to the output area
                if (e.getSource() == humidityRadio) {
                    outputTextArea.setText(arraylist.get(2));
                }

                //assigns basic set of weather info to the output area
                if (e.getSource() == basicInfoRadio) {
                    String basicOutput = arraylist.get(0) + " " + arraylist.get(1) + " " + arraylist.get(7);
                    outputTextArea.setText(basicOutput);
                }

                //assigns all sorted weather data to output area
                if (e.getSource() == extraRadio) {
                    outputTextArea.setText(arraylist.toString());
                }

                //assigns zipcodes the user has searched to the output area using a linked list
                if (e.getSource() == citiesCheckRadio) {

                    map.put(arraylist.get(0), zipCodeField.getText());
                    if(!map.isEmpty()){
                        String citiesHistory = DataConversion.formatMapElements(map);
                        outputTextArea.setText(citiesHistory);
                    }
                }
            }

            //if zip code is not provided warn the user
            catch(NullPointerException nu) {
                JOptionPane.showMessageDialog(null, "A zip code must be entered before using this option.");
            }

            //if no data is found in the linked list then warn the user
            catch(IndexOutOfBoundsException iob) {
                JOptionPane.showMessageDialog(null, "Enter a zip code!");
            }
        }
    }

    /**
     * This class resets every component to the default state
     */
    class ClearButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == clearButton) {
                    arraylist.clear();
                    arraylist.add("");
                    map.clear();
                    outputTextArea.setText("");
                    zipCodeField.setText("");
                    group.clearSelection();
                }

                //if no data was entered warn the user
            } catch(NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "Nothing to be cleared");
            }
        }
    }
}
