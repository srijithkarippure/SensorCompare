package com.sase.fcs.assignment1;

import java.util.Date;

/**
 * @author Srijith
 * Sensor bean class with attributes
 */
public class Sensor {
  /**
   * Sensor Id of the sensor.
   */
  private String sensorId;
  /**
   * Location at which the sensor is present.
   */
  private String location;
  /**
   * Time at which the temperature was noted.
   */
  private Date timeStamp;
  /**
   * Temperature emitted by the sensor.
   */
  private float temperatureValue;
  /**
   * Getter method for sensorId.
   * @return SensorId
   */
  public final String getSensorId() {
    return sensorId;
  }
  /**
   * Setter method for SensorId.
   * @param id Id of the sensor
   */
  public final void setSensorId(final String id) {
    this.sensorId = id;
  }
  /**
   * Getter for location attribute.
   * @return location
   */
  public final String getLocation() {
    return location;
  }
  /**
   * setter for location.
   * @param loc Location
   */
  public final void setLocation(final String loc) {
    this.location = loc;
  }
  /**
   * Getter for timestamp attribute.
   * @return timestamp
   */
  public final Date getTimeStamp() {
    return timeStamp;
  }
  /**
   * Setter for timestamp.
   * @param ts timestamp
   */
  public final void setTimeStamp(final Date ts) {
    this.timeStamp = ts;
  }
  /**
   * Getter for temperature.
   * @return temperature
   */
  public final float getTemperatureValue() {
    return temperatureValue;
  }
  /**
   * Setter for temperature.
   * @param tempValue Temperature
   */
  public final void setTemperatureValue(final float tempValue) {
    this.temperatureValue = tempValue;
  }

}
