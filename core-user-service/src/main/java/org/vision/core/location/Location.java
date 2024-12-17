package org.vision.core.location;

import org.vision.core.common.VisionObject;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Location extends VisionObject {

    BigDecimal latitude;
    BigDecimal longitude;
    Timestamp locationTime;
    String message;
    String accuracy;

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Timestamp getLocationTime() {
        return locationTime;
    }

    public void setLocationTime(Timestamp locationTime) {
        this.locationTime = locationTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }
}
