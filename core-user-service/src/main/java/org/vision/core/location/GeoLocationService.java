package org.vision.core.location;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class GeoLocationService {

    public static Map<String, Object> fetchCurrentLocation() {
        String apiKey = "AIzaSyBlY8A_tSkdt4yrS_rLLJvMirDdvc4XTlM"; // Replace with your Google API key
        String url = "https://www.googleapis.com/geolocation/v1/geolocate?key=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = new HashMap<>();
        request.put("considerIp", true); // Use IP for geolocation

        // Fetch response from Google API
        Map<String, Object> response = restTemplate.postForObject(url, request, Map.class);

        // Extract latitude and longitude
        if (response != null && response.get("location") != null) {
            Map<String, Double> location = (Map<String, Double>) response.get("location");
            double latitude = location.get("lat");
            double longitude = location.get("lng");

            // Return location data
            Map<String, Object> result = new HashMap<>();
            result.put("latitude", latitude);
            result.put("longitude", longitude);
            result.put("accuracy", response.get("accuracy"));

            return result;
        }
        return null;
    }
}

