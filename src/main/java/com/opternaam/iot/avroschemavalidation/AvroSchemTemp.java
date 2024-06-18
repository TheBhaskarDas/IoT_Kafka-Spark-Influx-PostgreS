package com.opternaam.iot.avroschemavalidation;

public class AvroSchemTemp {
	private String CustomerId;
	private String DeviceId;
	private String DateFromDevice;
	private Double ValueTemperature;
	
	
	public AvroSchemTemp(String customerId, String deviceId, String dateFromDevice, Double valueTemperature) {
		super();
		CustomerId = customerId;
		DeviceId = deviceId;
		DateFromDevice = dateFromDevice;
		ValueTemperature = valueTemperature;
	}
	public String getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}
	public String getDeviceId() {
		return DeviceId;
	}
	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}
	public String getDateFromDevice() {
		return DateFromDevice;
	}
	public void setDateFromDevice(String dateFromDevice) {
		DateFromDevice = dateFromDevice;
	}
	public Double getValueTemperature() {
		return ValueTemperature;
	}
	public void setValueTemperature(Double valueTemperature) {
		ValueTemperature = valueTemperature;
	}
	
	
}
