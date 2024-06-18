package com.opternaam.iot.sparkintegrator;

/*
private static final String topic= "invalid";
private static String device_id=null,cust_id=null,devices_details=null;
private static float value_temp = 0.0f,value_hum = 0.0f;
private static Date date =null;
*/
public class JsonBean {
	private String topic;
	private String deviceId;
	private String custId;
	private String devicesDetails;
	private String date;
	private String valueTemp;
	private String valueHum;
	private String valueVibration;
	
	
	
	public JsonBean(String topic, String deviceId, String custId, String devicesDetails, String date, String valueTemp,
			String valueHum) {
		super();
		this.topic = topic;
		this.deviceId = deviceId;
		this.custId = custId;
		this.devicesDetails = devicesDetails;
		this.date = date;
		this.valueTemp = valueTemp;
		this.valueHum = valueHum;
	}
	
	public JsonBean(String topic, String deviceId, String custId, String devicesDetails, String date, String valueTemp,
			String valueHum, String valueVibration) {
		super();
		this.topic = topic;
		this.deviceId = deviceId;
		this.custId = custId;
		this.devicesDetails = devicesDetails;
		this.date = date;
		this.valueTemp = valueTemp;
		this.valueHum = valueHum;
		this.valueVibration = valueVibration;
	}



	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getDevicesDetails() {
		return devicesDetails;
	}
	public void setDevicesDetails(String devicesDetails) {
		this.devicesDetails = devicesDetails;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getValueTemp() {
		return valueTemp;
	}
	public void setValueTemp(String valueTemp) {
		this.valueTemp = valueTemp;
	}
	public String getValueHum() {
		return valueHum;
	}
	public void setValueHum(String valueHum) {
		this.valueHum = valueHum;
	}
	public String getValueVibration() {
		return valueVibration;
	}
	public void setValueVibration(String valueVibration) {
		this.valueVibration = valueVibration;
	}
	
}
