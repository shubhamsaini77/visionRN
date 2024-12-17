package org.vision.core.location;

import org.vision.core.common.CommonResponse;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class LocationHelper {
    public Location getCurrentLocation(String locale) throws Exception {
        Location location = new Location();
        LocalDateTime now = LocalDateTime.now();

        double latitude = 0;
        double longitude = 0;

        // Format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        Map<String, Object> response = GeoLocationService.fetchCurrentLocation();

        if (response != null) {
            latitude = (double) response.get("latitude");  // Example: San Francisco Latitude
            longitude = (double) response.get("longitude");  // Example: San Francisco Latitude
            String accuracy = String.valueOf(response.get("accuracy"));
            location.setAccuracy(accuracy);
            location.setMessage("Current location fetched successfully");
        } else {
            location.setAccuracy("0");
            location.setMessage("Current location fetch failed");
        }

        location.setLatitude(BigDecimal.valueOf(latitude));
        location.setLongitude(BigDecimal.valueOf(longitude));
        location.setLocationTime(Timestamp.valueOf(formattedDateTime));


        return location;
    }

    public CommonResponse saveLocation(String locale, Location location) throws Exception {
        CommonResponse commonResponse = new CommonResponse();
        LocationDataAccess dataAccess = new LocationDataAccess();

        try {
            location = dataAccess.saveLocation(locale, location);
            commonResponse.setResponseObject(location);
        } catch (Throwable e) {
            commonResponse.setResponseCode("400");
            commonResponse.setResponseMessage("Failure");
            commonResponse.setValidRequest(false);

            e.printStackTrace();
        }

        return commonResponse;
    }

}
