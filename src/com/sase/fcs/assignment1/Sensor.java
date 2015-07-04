package com.sase.fcs.assignment1;

import java.util.Date;

public class Sensor {
	String sensorId;
	String location;
	Date timeStamp;
	float temperatureValue;
	
	public String getSensorId() {
		return sensorId;
	}
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public float getTemperatureValue() {
		return temperatureValue;
	}
	public void setTemperatureValue(float temperatureValue) {
		this.temperatureValue = temperatureValue;
	}
	
}
