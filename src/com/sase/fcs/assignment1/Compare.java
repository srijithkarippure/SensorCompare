package com.sase.fcs.assignment1;

public class Compare implements Comparable<Sensor> {
	Sensor maxSensor = new Sensor();
	Sensor minSensor = new Sensor();
	
	public Compare() {
		maxSensor.setTemperatureValue(Float.MIN_VALUE);
		minSensor.setTemperatureValue(Float.MAX_VALUE);
	}

	public static void main(String[] args) {
		Compare c = new Compare();
		int temp=1;
		Sensor s;
		for (int i=0;i<100000000;i++){
			s = new Sensor();
			s.setTemperatureValue(temp);
			c.compareTo(s);
			temp++;
		}
		System.out.println("Max Temp Value:" + c.maxSensor.getTemperatureValue());
		System.out.println("Min Temp Value:" + c.minSensor.getTemperatureValue() );
	}

	public int compareTo(Sensor s) {
		if (s.getTemperatureValue() > maxSensor.getTemperatureValue()) {
			maxSensor.setTemperatureValue(s.getTemperatureValue());
		}
		if (s.getTemperatureValue() < minSensor.getTemperatureValue()) {
			minSensor.setTemperatureValue(s.getTemperatureValue());
		}
		return 0;
	}

}
