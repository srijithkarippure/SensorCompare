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
 * 
 * @author Srijith
 * Reads sensor data from file, populates to sensor objects and adds it to list
 * Compares Sensor data pair wise and finds the sensor with max and min temperature value
 * 
 */
public class Compare implements Comparator<Sensor> {
	Sensor maxSensor;
	Sensor minSensor;
	int i = 0;
	public static void main(String[] args) throws IOException, ParseException {

		Sensor s;
		BufferedReader br = null;
		try {
			Compare c = new Compare();
			br = new BufferedReader(new FileReader(new File("input.txt")));
			List<Sensor> allSensors = new ArrayList<Sensor>();
			int iter = 0;
			while (br.ready()) {

				String sensorArray[] = br.readLine().split(",");
				if (sensorArray.length != 4) {
					System.out
							.println("Invalid input, proceeding with the next input");
					continue;
				}
				s = new Sensor();
				s.setSensorId(sensorArray[0]);
				s.setLocation(sensorArray[1]);
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				s.setTimeStamp(fmt.parse(sensorArray[2]));
				s.setTemperatureValue(Float.parseFloat(sensorArray[3]));
				allSensors.add(s);
			}
			int totalNoOfSensors = allSensors.size();
			if (totalNoOfSensors == 0) {
				System.out
						.println("No input provided, exiting the application");
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
						c.i++;
						if (firstSensor.getTemperatureValue() > secondSensor
								.getTemperatureValue()) {
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
			System.out.println("Max Temp Value:"
					+ c.maxSensor.getTemperatureValue());
			System.out.println("Min Temp Value:"
					+ c.minSensor.getTemperatureValue());
			System.out.println("No of Comparisons: " + c.i);
		} finally {
			br.close();
		}
	}

	public int compare(Sensor s1, Sensor s2) {
		i++;
		if (s1.getTemperatureValue() > s2.getTemperatureValue()) {
			i++;
			if (s1.getTemperatureValue() > maxSensor.getTemperatureValue()) {
				maxSensor = s1;
				i++;
				if (s2.getTemperatureValue() < minSensor.getTemperatureValue())
					minSensor = s2;
			}
		} else {
			i++;
			if (s2.getTemperatureValue() > maxSensor.getTemperatureValue()) {
				maxSensor = s2;
				i++;
				if (s1.getTemperatureValue() < minSensor.getTemperatureValue())
					minSensor = s1;
			}
		}
		return 0;
	}

}
