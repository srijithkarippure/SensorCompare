package com.sase.fcs.assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class for Comparing Sensor Data.
 *
 * @author Srijith
 * Reads sensor data from file, populates
 * sensor objects and adds it to list
 * Compares Sensor data pair wise and
 * finds the sensor with max and min temperature value
 */
public class Compare implements Comparator<Sensor> {
  /**
   * temperature is stored as 4th column in the file.
   * This value is used to retrieve temp value from
   * file
   */
  private static final int SENSOR_TEMPERATURE_COLUMN = 3;
  /**
   * Each sensor must have 4 attributes.
   */
  private static final int REQUIRED_SENSOR_DETAILS = 4;
  /**
   * Sensor with max temperature.
   */
  private Sensor maxSensor;
  /**
   * Sensor with min temperature.
   */
  private Sensor minSensor;
  /**
   * Counter to count number of comparisons.
   */
  private static int i = 0;

  /**
   * Reads sensor data from file.
   * populates sensor objects and stores
   * in an array list. Compares
   * temperature os sensors pair wise and
   * finds sensors generating max and min temps
   *
   * @param args
   *          Not used
   * @throws IOException
   *           File related exceptions
   * @throws ParseException
   *           File Related Exception
   */
  public static void main(final String[] args)
      throws IOException, ParseException {

    Sensor s;
    BufferedReader br = null;
    try {
      Compare c = new Compare();
      br = new BufferedReader(new FileReader(new File("input.txt")));
      List<Sensor> allSensors = new ArrayList<Sensor>();
      int iter = 0;
      String readLine = br.readLine();
      while (readLine != null) {

        String[] sensorArray = readLine.split(",");
        if (sensorArray.length != REQUIRED_SENSOR_DETAILS) {
          System.out.println("Invalid input, proceeding with the next input");
          continue;
        }
        s = new Sensor();
        s.setSensorId(sensorArray[0]);
        s.setLocation(sensorArray[1]);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        s.setTimeStamp(fmt.parse(sensorArray[2]));
        s.setTemperatureValue(Float.parseFloat(
            sensorArray[SENSOR_TEMPERATURE_COLUMN]));
        allSensors.add(s);
        readLine = br.readLine();
      }
      int totalNoOfSensors = allSensors.size();
      if (totalNoOfSensors == 0) {
        System.out.println("No input provided, exiting the application");
        System.exit(0);
      } else {
        Sensor firstSensor = allSensors.get(0);
        if (totalNoOfSensors == 1) {
          c.maxSensor = firstSensor;
          c.minSensor = firstSensor;
        } else {
          if (totalNoOfSensors % 2 != 0) {
            c.maxSensor = firstSensor;
            c.minSensor = firstSensor;
            iter = 1;
          } else {
            Sensor secondSensor = allSensors.get(1);
            Compare.i++;
            if (firstSensor.getTemperatureValue()
                > secondSensor.getTemperatureValue()) {
              c.maxSensor = firstSensor;
              c.minSensor = secondSensor;

            } else {
              c.maxSensor = secondSensor;
              c.minSensor = firstSensor;
            }
            iter = 2;
          }
        }
      }

      while (iter < totalNoOfSensors - 1) {
        c.compare(allSensors.get(iter), allSensors.get(iter + 1));
        iter += 2;
      }
      System.out.println("Max Temp Value:" + c.maxSensor.getTemperatureValue());
      System.out.println("Min Temp Value:" + c.minSensor.getTemperatureValue());
      System.out.println("No of Comparisons: " + Compare.i);
    } finally {
      try {
        br.close();
      } catch (Exception e) {
        System.out.println("Buffered Reader close Exception");
      }
    }
  }
  /**
   * Compares two sensor objects and finds the min and max.
   * Then compares the min with actual MinSensor
   * and max with actual maxSensor and changes value accordingly
   * @param s1 Sensor-1
   * @param s2 Sensor-2
   * @return 0 used for nothing
   */
  public final int compare(final Sensor s1, final Sensor s2) {
    i++;
    if (s1.getTemperatureValue() > s2.getTemperatureValue()) {
      i++;
      if (s1.getTemperatureValue() > maxSensor.getTemperatureValue()) {
        maxSensor = s1;
        i++;
        if (s2.getTemperatureValue() < minSensor.getTemperatureValue()) {
          minSensor = s2;
        }
      }
    } else {
      i++;
      if (s2.getTemperatureValue() > maxSensor.getTemperatureValue()) {
        maxSensor = s2;
        i++;
        if (s1.getTemperatureValue() < minSensor.getTemperatureValue()) {
          minSensor = s1;
        }
      }
    }
    return 0;
  }
}
