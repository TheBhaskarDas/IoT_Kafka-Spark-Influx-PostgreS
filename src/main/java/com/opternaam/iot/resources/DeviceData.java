package com.opternaam.iot.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * DeviceData
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "deviceID",
    "temperature",
    "humidity",
    "date",
    "cutomerID",
    "deviceDesc"
})
public class DeviceData {

    @JsonProperty("deviceID")
    private String deviceID;
    @JsonProperty("temperature")
    private Object temperature;
    @JsonProperty("humidity")
    private Object humidity;
    @JsonProperty("date")
    private String date;
    @JsonProperty("cutomerID")
    private String cutomerID;
    @JsonProperty("deviceDesc")
    private String deviceDesc;

    @JsonProperty("deviceID")
    public String getDeviceID() {
        return deviceID;
    }

    @JsonProperty("deviceID")
    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    @JsonProperty("temperature")
    public Object getTemperature() {
        return temperature;
    }

    @JsonProperty("temperature")
    public void setTemperature(Object temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("humidity")
    public Object getHumidity() {
        return humidity;
    }

    @JsonProperty("humidity")
    public void setHumidity(Object humidity) {
        this.humidity = humidity;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("cutomerID")
    public String getCutomerID() {
        return cutomerID;
    }

    @JsonProperty("cutomerID")
    public void setCutomerID(String cutomerID) {
        this.cutomerID = cutomerID;
    }

    @JsonProperty("deviceDesc")
    public String getDeviceDesc() {
        return deviceDesc;
    }

    @JsonProperty("deviceDesc")
    public void setDeviceDesc(String deviceDesc) {
        this.deviceDesc = deviceDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("deviceID", deviceID).append("temperature", temperature).append("humidity", humidity).append("date", date).append("cutomerID", cutomerID).append("deviceDesc", deviceDesc).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(date).append(cutomerID).append(temperature).append(humidity).append(deviceID).append(deviceDesc).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DeviceData) == false) {
            return false;
        }
        DeviceData rhs = ((DeviceData) other);
        return new EqualsBuilder().append(date, rhs.date).append(cutomerID, rhs.cutomerID).append(temperature, rhs.temperature).append(humidity, rhs.humidity).append(deviceID, rhs.deviceID).append(deviceDesc, rhs.deviceDesc).isEquals();
    }

}
